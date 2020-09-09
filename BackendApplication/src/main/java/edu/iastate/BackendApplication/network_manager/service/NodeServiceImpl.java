package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.model.NodeModel;

import java.util.List;

/**
 * The default implementation of NodeService.
 * TODO: Make this communicate with the actual network instead of returning test data.
 *
 * @author Grayson Cox
 */
public class NodeServiceImpl implements NodeService {

	private final List<NodeModel> testNodes = List.of(
			new NodeModel(1L, "RELAY", "Relay 1", "192.168.1.7", 78),
			new NodeModel(2L, "RELAY", "Relay 2", "192.168.1.3", 24),
			new NodeModel(3L, "RELAY", "Relay 3", "192.168.1.24", 68),
			new NodeModel(4L, "RELAY", "Relay 4", "192.168.1.6", 69),
			new NodeModel(5L, "CAMERA", "Camera 1", "192.168.1.17", 94),
			new NodeModel(6L, "CAMERA", "Camera 2", "192.168.1.2", 74));

	@Override
	public List<NodeModel> getAllNodes() {
		return testNodes;
	}

	@Override
	public NodeModel getNodeById(Long nodeId) {
		return testNodes.stream()
				.filter(nodeModel -> nodeModel.getId().equals(nodeId))
				.findFirst()
				.orElse(null);
	}

}
