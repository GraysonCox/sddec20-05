package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;

import java.util.List;

/**
 * Provides methods for viewing active nodes in the network.
 */
public interface NodeService {

	/**
	 * Returns a list containing all the active nodes in the network.
	 *
	 * @return A list containing all the active nodes in the network.
	 * @throws NodeServiceException If there is an exception while fetching all the nodes in the network.
	 */
	List<NodeModel> getAllNodes() throws NodeServiceException;

	/**
	 * Returns the node with the given IPv4 address, or null if the node does not exist.
	 *
	 * @param ipAddress An IPv4 address.
	 * @return The node with the given ID, or null if the node does not exist.
	 * @throws NodeServiceException If there is an exception while fetching the desired node.
	 */
	NodeModel getNodeByIpAddress(String ipAddress) throws NodeServiceException;

	/**
	 * Updates the node with the given IP address.
	 *
	 * @param ipAddress The IPv4 address of the node to update.
	 * @param node      The node to persist.
	 * @throws NodeServiceException If there is an exception while updating the node.
	 */
	void updateNode(String ipAddress, NodeModel node) throws NodeServiceException;

}
