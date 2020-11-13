package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.config.NetworkManagerConfigurationProperties;
import edu.iastate.BackendApplication.network_manager.exception.NmapClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Handles nmap commands.
 */
public class NmapClient {

	private static final Logger logger = LoggerFactory.getLogger(NmapClient.class);

	private final Runtime runtime;
	private final NetworkManagerConfigurationProperties configurationProperties;

	public NmapClient(Runtime runtime, NetworkManagerConfigurationProperties networkManagerConfigurationProperties) {
		this.runtime = runtime;
		this.configurationProperties = networkManagerConfigurationProperties;
	}

	/**
	 * Performs an nmap scan and returns the IP addresses of the devices in the network.
	 *
	 * @return A list of the IP addresses of the devices in the network.
	 * @throws NmapClientException If there is an exception while executing the system command.
	 */
	public List<String> getIpAddressesInNetwork() throws NmapClientException {
		logger.info("Scanning for nodes in network range {}...", configurationProperties.getNetworkRange());
		List<String> ipAddresses = new ArrayList<>();
		Process process;
		try {
			process = executeNmapCommand(configurationProperties.getNetworkRange(),
										 configurationProperties.getNetworkScanTimeout());
		} catch (IOException | InterruptedException e) {
			throw NmapClientException.withMessage("There was an exception while executing the nmap scan.", e);
		}

		if (process.exitValue() != 0) {
			String errorMessage = String.join("\n", getLinesFromInputStream(process.getErrorStream()));
			logger.warn(errorMessage);
		}

		final String ipAddressLinePrefix = "Nmap scan report for ";
		List<String> resultLines = getLinesFromInputStream(process.getInputStream());
		logger.info("Received results from nmap scan: {}", String.join("\n", resultLines));
		for (String line : resultLines) {
			if (line.startsWith(ipAddressLinePrefix)) {
				String ipAddress = line.substring(ipAddressLinePrefix.length());
				ipAddresses.add(ipAddress);
			}
		}

		return ipAddresses;
	}

	/**
	 * Executes the nmap scan, waits for the process to exit, and then returns the process.
	 *
	 * @param networkRange A string of the form "IPv4 address"/"netmask" (e.g., "192.172.0.0/24").
	 * @param timeout      The maximum time to wait for the nmap scan to complete.
	 * @return The process object.
	 * @throws IOException          If there is an exception while executing the command.
	 * @throws InterruptedException If there is an exception while waiting for the command process to exit.
	 */
	private Process executeNmapCommand(String networkRange, long timeout) throws IOException, InterruptedException {
		String nmapCommand = "nmap -sn " + networkRange;
		Process process = runtime.exec(nmapCommand);
		process.waitFor(timeout, TimeUnit.MILLISECONDS);
		return process;
	}

	/**
	 * Compiles all the lines from the given input stream into a list.
	 *
	 * @param inputStream An input stream.
	 * @return A list containing all the lines from the given input stream into a list.
	 */
	private List<String> getLinesFromInputStream(InputStream inputStream) {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.lines().collect(Collectors.toList());
	}

}
