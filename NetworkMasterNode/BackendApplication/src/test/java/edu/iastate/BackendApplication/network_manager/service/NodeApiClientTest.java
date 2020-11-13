package edu.iastate.BackendApplication.network_manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iastate.BackendApplication.network_manager.exception.NodeApiClientException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import edu.iastate.BackendApplication.network_manager.model.NodeType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Unit test for NodeApiClient.
 */
class NodeApiClientTest {

	private NodeApiClient nodeApiClient;

	@Mock
	private HttpClient httpClient;

	@Mock
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		nodeApiClient = new NodeApiClient(httpClient, objectMapper);
	}

	@Test
	void getNodeByIpAddress_shouldReturnNode() throws IOException, NodeApiClientException {
		String ipAddress = "123.123.0.1";

		NodeModel expectedResult = new NodeModel();
		expectedResult.setIpAddress(ipAddress);
		expectedResult.setName("Test Node 1");
		expectedResult.setType(NodeType.RELAY);
		expectedResult.setNetworkName("Test Network 1");
		expectedResult.setBatteryPercentage(100);

		String expectedResultJson = new ObjectMapper().writeValueAsString(expectedResult);

		HttpResponse response = mockHttpResponse(expectedResultJson, 200);
		doReturn(response).when(httpClient).execute(Mockito.any(HttpGet.class));

		doReturn(expectedResult).when(objectMapper).readValue(expectedResultJson, NodeModel.class);

		NodeModel actualResult = nodeApiClient.getNodeByIpAddress(ipAddress);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void getNodeByIpAddress_badHttpStatusCode_shouldThrowException() throws IOException {
		String ipAddress = "123.123.0.1";

		HttpResponse response = mockHttpResponse("", 404);
		doReturn(response).when(httpClient).execute(Mockito.any(HttpGet.class));

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.getNodeByIpAddress(ipAddress));
	}

	@Test
	void getNodeByIpAddress_httpClientThrows_shouldReturnNull() throws IOException, NodeApiClientException {
		String ipAddress = "123.123.0.1";

		doThrow(mock(IOException.class)).when(httpClient).execute(Mockito.any(HttpGet.class));

		NodeModel actualResult = nodeApiClient.getNodeByIpAddress(ipAddress);

		assertThat(actualResult).isNull();
	}

	@Test
	void getNodeByIpAddress_objectMapperThrows_shouldThrowException() throws IOException {
		String ipAddress = "123.123.0.1";

		NodeModel expectedResult = new NodeModel();
		expectedResult.setIpAddress(ipAddress);
		expectedResult.setName("Test Node 1");
		expectedResult.setType(NodeType.RELAY);
		expectedResult.setNetworkName("Test Network 1");
		expectedResult.setBatteryPercentage(100);

		String expectedResultJson = new ObjectMapper().writeValueAsString(expectedResult);

		HttpResponse response = mockHttpResponse(expectedResultJson, 200);
		doReturn(response).when(httpClient).execute(Mockito.any(HttpGet.class));

		doThrow(mock(JsonMappingException.class)).when(objectMapper).readValue(expectedResultJson, NodeModel.class);

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.getNodeByIpAddress(ipAddress));
	}

	@Test
	void updateNode_shouldNotThrowException() throws NodeApiClientException, IOException {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		String nodeJson = new ObjectMapper().writeValueAsString(node);

		doReturn(nodeJson).when(objectMapper).writeValueAsString(node);

		HttpResponse response = mockHttpResponse("", 200);
		doReturn(response).when(httpClient).execute(Mockito.any(HttpPost.class));

		nodeApiClient.updateNode(node);
	}

	@Test
	void updateNode_badHttpStatusCode_shouldThrowException() throws IOException {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		String nodeJson = new ObjectMapper().writeValueAsString(node);

		doReturn(nodeJson).when(objectMapper).writeValueAsString(node);

		HttpResponse response = mockHttpResponse("", 404);
		doReturn(response).when(httpClient).execute(Mockito.any(HttpPost.class));

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.updateNode(node));
	}

	@Test
	void updateNode_invalidIpAddress_shouldThrowException() {
		NodeModel node = new NodeModel();
		node.setIpAddress("INVALID_IP_ADDRESS");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.updateNode(node));
	}

	@Test
	void updateNode_invalidName_shouldThrowException() {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("A name that contains too many characters");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.updateNode(node));
	}

	@Test
	void updateNode_invalidNetworkName_shouldThrowException() {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("A name that contains too many characters");
		node.setBatteryPercentage(100);

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.updateNode(node));
	}

	@Test
	void updateNode_objectMapperThrows_shouldThrowException() throws IOException {
		NodeModel node = new NodeModel();

		doThrow(mock(JsonProcessingException.class)).when(objectMapper).writeValueAsString(node);

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.updateNode(node));
	}

	@Test
	void updateNode_httpClientThrows_shouldThrowException() throws IOException {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		String nodeJson = new ObjectMapper().writeValueAsString(node);

		doReturn(nodeJson).when(objectMapper).writeValueAsString(node);

		doThrow(mock(IOException.class)).when(httpClient).execute(Mockito.any(HttpPost.class));

		assertThatExceptionOfType(NodeApiClientException.class)
				.isThrownBy(() -> nodeApiClient.updateNode(node));
	}

	private HttpResponse mockHttpResponse(String body, int statusCode) throws IOException {
		HttpEntity httpEntity = mock(HttpEntity.class);
		doReturn(new ByteArrayInputStream(body.getBytes())).when(httpEntity).getContent();

		StatusLine statusLine = mock(StatusLine.class);
		doReturn(statusCode).when(statusLine).getStatusCode();

		HttpResponse response = mock(HttpResponse.class);
		doReturn(httpEntity).when(response).getEntity();
		doReturn(statusLine).when(response).getStatusLine();

		return response;
	}

}
