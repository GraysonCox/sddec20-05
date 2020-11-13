package edu.iastate.BackendApplication.network_manager.controller;

import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import edu.iastate.BackendApplication.network_manager.service.NodeService;

import java.util.List;

/**
 * The default implementation of NodeController.
 */
public class NodeControllerImpl implements NodeController {

	private final NodeService nodeService;

	public NodeControllerImpl(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	@Override
	public List<NodeModel> getAllNodes() throws NodeServiceException {
		return nodeService.getAllNodes();
	}

	@Override
	public NodeModel getNodeByIpAddress(String ipAddress) throws NodeServiceException {
		return nodeService.getNodeByIpAddress(ipAddress);
	}

	@Override
	public void updateNode(String ipAddress, NodeModel node) throws NodeServiceException {
		nodeService.updateNode(ipAddress, node);
	}

}
