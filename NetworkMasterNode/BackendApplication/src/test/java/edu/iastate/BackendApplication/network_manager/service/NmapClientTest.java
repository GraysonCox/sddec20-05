package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.config.NetworkManagerConfigurationProperties;
import edu.iastate.BackendApplication.network_manager.exception.NmapClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for NmapClient.
 */
class NmapClientTest {

	private NmapClient nmapClient;

	@Mock
	private Runtime runtime;

	@Mock
	private NetworkManagerConfigurationProperties configurationProperties;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		when(configurationProperties.getNetworkRange()).thenReturn("192.172.0.0/24");
		when(configurationProperties.getNetworkScanTimeout()).thenReturn(10000L);

		nmapClient = new NmapClient(runtime, configurationProperties);
	}

	@Test
	void getIpAddressesInNetwork_shouldSucceed() throws IOException, NmapClientException {
		String nmapScanResult = "Starting Nmap 7.91 ( https://nmap.org ) at 2020-11-08 13:39 CST\n" +
								"Nmap scan report for 192.168.0.1\n" +
								"Host is up (0.10s latency).\n" +
								"MAC Address: 76:5A:9E:00:46:15 (Technicolor CH USA)\n" +
								"Nmap scan report for 192.168.0.2\n" +
								"Host is up (0.10s latency).\n" +
								"MAC Address: 70:5A:4E:00:46:16 (Technicolor CH USA)\n" +
								"Nmap scan report for 192.168.0.16\n" +
								"Host is up (0.013s latency).\n" +
								"MAC Address: E4:F0:42:5D:64:58 (Google)\n" +
								"Nmap scan report for 192.168.0.17\n" +
								"Host is up (0.043s latency).\n" +
								"MAC Address: D4:F5:47:36:69:CD (Google)\n" +
								"Nmap scan report for 192.168.0.11\n" +
								"Host is up.\n" +
								"Nmap done: 256 IP addresses (5 hosts up) scanned in 4.53 seconds";

		Process process = mockNmapScanProcess(nmapScanResult, "", 0);
		doReturn(process)
				.when(runtime)
				.exec(anyString());

		List<String> actualResult = nmapClient.getIpAddressesInNetwork();

		assertThat(actualResult)
				.containsExactly("192.168.0.1", "192.168.0.2", "192.168.0.16", "192.168.0.17", "192.168.0.11");
	}

	@Test
	void getIpAddressesInNetwork_runtimeThrows_shouldThrowException() throws IOException {
		doThrow(new IOException())
				.when(runtime)
				.exec(anyString());

		assertThatExceptionOfType(NmapClientException.class)
				.isThrownBy(() -> nmapClient.getIpAddressesInNetwork());
	}

	@Test
	void getIpAddressesInNetwork_nonZeroExitValue_shouldSucceed() throws IOException, NmapClientException {
		String nmapScanResult = "Starting Nmap 7.91 ( https://nmap.org ) at 2020-11-08 13:39 CST\n" +
								"Nmap scan report for 192.168.0.1\n" +
								"Host is up (0.10s latency).\n" +
								"MAC Address: 76:5A:9E:00:46:15 (Technicolor CH USA)\n" +
								"Nmap scan report for 192.168.0.2\n" +
								"Host is up (0.10s latency).\n" +
								"MAC Address: 70:5A:4E:00:46:16 (Technicolor CH USA)\n" +
								"Nmap scan report for 192.168.0.16\n" +
								"Host is up (0.013s latency).\n" +
								"MAC Address: E4:F0:42:5D:64:58 (Google)\n" +
								"Nmap scan report for 192.168.0.17\n" +
								"Host is up (0.043s latency).\n" +
								"MAC Address: D4:F5:47:36:69:CD (Google)\n" +
								"Nmap scan report for 192.168.0.11\n" +
								"Host is up.\n" +
								"Nmap done: 256 IP addresses (5 hosts up) scanned in 4.53 seconds";

		Process process = mockNmapScanProcess(nmapScanResult, "", 1);
		doReturn(process)
				.when(runtime)
				.exec(anyString());

		List<String> actualResult = nmapClient.getIpAddressesInNetwork();

		assertThat(actualResult)
				.containsExactly("192.168.0.1", "192.168.0.2", "192.168.0.16", "192.168.0.17", "192.168.0.11");
	}

	@Test
	void getIpAddressesInNetwork_emptyResult_shouldReturnEmpty() throws IOException, NmapClientException {
		String nmapScanResult = "";

		Process process = mockNmapScanProcess(nmapScanResult, "ERROR", 1);
		doReturn(process)
				.when(runtime)
				.exec(anyString());

		List<String> actualResult = nmapClient.getIpAddressesInNetwork();

		assertThat(actualResult).isEmpty();
	}

	/**
	 * Creates a mocked Process whose properties will be populated with the given values.
	 *
	 * @param inputStream The string to be used as the Process's input stream.
	 * @param errorStream The string to be used as the Process's error stream.
	 * @param exitValue   The exit code of the process.
	 * @return The mocked Process.
	 */
	private Process mockNmapScanProcess(String inputStream, String errorStream, int exitValue) {
		Process process = mock(Process.class);
		doReturn(new ByteArrayInputStream(inputStream.getBytes()))
				.when(process)
				.getInputStream();
		doReturn(new ByteArrayInputStream(errorStream.getBytes()))
				.when(process)
				.getErrorStream();
		doReturn(exitValue).when(process).exitValue();
		return process;
	}

}
