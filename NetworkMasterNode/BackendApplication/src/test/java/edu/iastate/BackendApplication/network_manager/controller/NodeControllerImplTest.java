package edu.iastate.BackendApplication.network_manager.controller;

import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import edu.iastate.BackendApplication.network_manager.service.NodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test for NodeControllerImpl.
 */
class NodeControllerImplTest {

	private NodeControllerImpl nodeController;

	@Mock
	private NodeService nodeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		nodeController = new NodeControllerImpl(nodeService);
	}

	@Test
	void getAllNodes_success() throws NodeServiceException {
		List<NodeModel> expectedResult = Collections.singletonList(new NodeModel());

		doReturn(expectedResult).when(nodeService).getAllNodes();

		List<NodeModel> actualResult = nodeController.getAllNodes();

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void getAllNodes_throwException() throws NodeServiceException {
		doThrow(mock(NodeServiceException.class)).when(nodeService).getAllNodes();

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeController.getAllNodes());
	}

	@Test
	void getNodeByIpAddress_success() throws NodeServiceException {
		String ipAddress = "123.123.0.1";

		NodeModel expectedResult = new NodeModel();
		expectedResult.setIpAddress(ipAddress);

		doReturn(expectedResult).when(nodeService).getNodeByIpAddress(ipAddress);

		NodeModel actualResult = nodeController.getNodeByIpAddress(ipAddress);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void getNodeByIpAddress_throwException() throws NodeServiceException {
		String ipAddress = "123.123.0.1";

		doThrow(mock(NodeServiceException.class)).when(nodeService).getNodeByIpAddress(ipAddress);

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeController.getNodeByIpAddress(ipAddress));
	}

	@Test
	void updateNode_success() throws NodeServiceException {
		NodeModel node = new NodeModel();

		nodeController.updateNode(node);

		verify(nodeService, times(1)).updateNode(node);
	}

	@Test
	void updateNode_throwException() throws NodeServiceException {
		NodeModel node = new NodeModel();

		doThrow(mock(NodeServiceException.class)).when(nodeService).updateNode(node);

		assertThatExceptionOfType(NodeServiceException.class)
				.isThrownBy(() -> nodeController.updateNode(node));
	}

}
