package edu.iastate.BackendApplication.network_manager.controller;

import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import edu.iastate.BackendApplication.network_manager.service.NodeService;

import java.util.List;

/**
 * The default implementation of NodeController.
 *
 * @author Grayson Cox
 */
public class NodeControllerImpl implements NodeController {

	private final NodeService nodeService;

	public NodeControllerImpl(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	@Override
	public List<NodeModel> getAllNodes() throws Exception {
		return nodeService.getAllNodes();
	}

	@Override
	public NodeModel getNodeById(Long nodeId) throws Exception {
		return nodeService.getNodeById(nodeId);
	}

}
