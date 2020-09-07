package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.model.NodeModel;

import java.util.List;

/**
 * Provides methods for viewing active nodes in the network.
 *
 * @author Grayson Cox
 */
public interface NodeService {

	/**
	 * Returns a list containing all the active nodes in the network.
	 *
	 * @return A list containing all the active nodes in the network.
	 * @throws Exception If there is an exception while fetching all the nodes in the network.
	 */
	List<NodeModel> getAllNodes() throws Exception;

	/**
	 * Returns the node with the given ID, or null if the node does not exist.
	 *
	 * @param nodeId The node ID.
	 * @return The node with the given ID, or null if the node does not exist.
	 * @throws Exception If there is an exception while fetching the desired node.
	 */
	NodeModel getNodeById(Long nodeId) throws Exception;

}
