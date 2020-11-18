package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.exception.NmapClientException;
import edu.iastate.BackendApplication.network_manager.exception.NodeApiClientException;
import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import edu.iastate.BackendApplication.network_manager.model.NodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Unit test for NodeServiceImpl.
 */
class NodeServiceImplTest {

	private NodeServiceImpl nodeService;

	@Mock
	private NmapClient nmapClient;

	@Mock
	private NodeApiClient nodeApiClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		nodeService = new NodeServiceImpl(nmapClient, nodeApiClient);
	}

	@Test
	void getAllNodes() throws NmapClientException, NodeApiClientException, NodeServiceException {
		NodeModel node1 = new NodeModel();
		node1.setIpAddress("123.123.0.1");
		NodeModel node2 = new NodeModel();
		node2.setIpAddress("123.123.0.2");
		NodeModel node3 = new NodeModel();
		node3.setIpAddress("123.123.0.3");
		List<NodeModel> expectedResult = Arrays.asList(node1, node2, node3);

		List<String> ipAddresses = expectedResult.stream().map(NodeModel::getIpAddress).collect(Collectors.toList());
		doReturn(ipAddresses).when(nmapClient).getIpAddressesInNetwork();

		doReturn(node1).when(nodeApiClient).getNodeByIpAddress(node1.getIpAddress());
		doReturn(node2).when(nodeApiClient).getNodeByIpAddress(node2.getIpAddress());
		doReturn(node3).when(nodeApiClient).getNodeByIpAddress(node3.getIpAddress());

		List<NodeModel> actualResult = nodeService.getAllNodes();

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void getAllNodes_nmapClientThrows() throws NmapClientException {
		doThrow(mock(NmapClientException.class))
				.when(nmapClient)
				.getIpAddressesInNetwork();

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeService.getAllNodes());
	}

	@Test
	void getAllNodes_nodeApiClientThrows() throws NmapClientException, NodeApiClientException {
		NodeModel node1 = new NodeModel();
		node1.setIpAddress("123.123.0.1");
		NodeModel node2 = new NodeModel();
		node2.setIpAddress("123.123.0.2");
		NodeModel node3 = new NodeModel();
		node3.setIpAddress("123.123.0.3");
		List<NodeModel> expectedResult = Arrays.asList(node1, node2, node3);

		List<String> ipAddresses = expectedResult.stream().map(NodeModel::getIpAddress).collect(Collectors.toList());
		doReturn(ipAddresses).when(nmapClient).getIpAddressesInNetwork();

		doThrow(mock(NodeApiClientException.class))
				.when(nodeApiClient)
				.getNodeByIpAddress(node1.getIpAddress());

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeService.getAllNodes());
	}

	@Test
	void getNodeByIpAddress_shouldReturnResult() throws NodeApiClientException, NodeServiceException {
		NodeModel expectedResult = new NodeModel();
		expectedResult.setIpAddress("123.123.0.1");

		doReturn(expectedResult).when(nodeApiClient).getNodeByIpAddress(expectedResult.getIpAddress());

		NodeModel actualResult = nodeService.getNodeByIpAddress(expectedResult.getIpAddress());

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void getNodeByIpAddress_invalidIpAddress_shouldThrowException() throws NodeApiClientException {
		NodeModel expectedResult = new NodeModel();
		expectedResult.setIpAddress("INVALID_IP_ADDRESS");

		doReturn(expectedResult).when(nodeApiClient).getNodeByIpAddress(expectedResult.getIpAddress());

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeService.getNodeByIpAddress(expectedResult.getIpAddress()));
	}

	@Test
	void getNodeByIpAddress_nodeApiClientThrows_shouldThrowException() throws NodeApiClientException {
		String ipAddress = "123.123.0.1";

		doThrow(mock(NodeApiClientException.class))
				.when(nodeApiClient)
				.getNodeByIpAddress(ipAddress);

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeService.getNodeByIpAddress(ipAddress));
	}

	@Test
	void updateNode_shouldUpdateSuccessfully() throws NodeServiceException, NodeApiClientException {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		nodeService.updateNode(node.getIpAddress(), node);

		Mockito.verify(nodeApiClient, Mockito.times(1)).updateNode(node);
	}

	@Test
	void updateNode_nodeApiClientThrows_shouldThrowException() throws NodeApiClientException {
		NodeModel node = new NodeModel();
		node.setIpAddress("123.123.0.1");
		node.setName("Test Node 1");
		node.setType(NodeType.RELAY);
		node.setNetworkName("Test Network 1");
		node.setBatteryPercentage(100);

		doThrow(mock(NodeApiClientException.class))
				.when(nodeApiClient)
				.updateNode(node);

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeService.updateNode(node.getIpAddress(), node));
	}

}
