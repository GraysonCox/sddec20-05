package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import edu.iastate.BackendApplication.network_manager.model.NodeType;

import java.util.List;

/**
 * The default implementation of NodeService.
 */
public class NodeServiceImpl implements NodeService {

	private List<NodeModel> testNodes = List.of(
			new NodeModel("192.168.1.7", "Master", NodeType.MASTER, "Network 1", 78),
			new NodeModel("192.168.1.3", "Relay 1", NodeType.RELAY, "Network 1", 24),
			new NodeModel("192.168.1.24", "Relay 2", NodeType.RELAY, "Network 1", 68),
			new NodeModel("192.168.1.6", "Relay 3", NodeType.RELAY, "Network 1", 69),
			new NodeModel("192.168.1.17", "Camera 1", NodeType.CAMERA, "Network 1", 94),
			new NodeModel("192.168.1.2", "Camera 2", NodeType.CAMERA, "Network 1", 74));

	@Override
	public List<NodeModel> getAllNodes() {
		return testNodes;
	}

	@Override
	public NodeModel getNodeByIpAddress(String ipAddress) {
		return testNodes.stream().filter(node -> node.getIpAddress().equals(ipAddress)).findFirst().orElse(null);
	}

	@Override
	public void updateNode(NodeModel node) {
		for (NodeModel liveNode : testNodes) {
			if (liveNode.getIpAddress().equals(node.getIpAddress())) {
				liveNode.setName(node.getName());
				liveNode.setNetworkName(node.getNetworkName());
			}
		}
	}

}
