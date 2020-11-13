package edu.iastate.BackendApplication.network_manager.controller;

import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provides a REST API for viewing active nodes in the network.
 */
@RestController
@RequestMapping("node")
public interface NodeController {

	/**
	 * Returns a list containing all the active nodes in the network.
	 *
	 * @return A list containing all the active nodes in the network.
	 * @throws NodeServiceException If there is an exception while fetching all the nodes in the network.
	 */
	@GetMapping
	List<NodeModel> getAllNodes() throws NodeServiceException;

	/**
	 * Returns the node with the given IPv4 address, or null if the node does not exist.
	 *
	 * @param ipAddress An IPv4 address.
	 * @return The node with the given ID, or null if the node does not exist.
	 * @throws NodeServiceException If there is an exception while fetching the desired node.
	 */
	@GetMapping("/{ipAddress}")
	NodeModel getNodeByIpAddress(@PathVariable String ipAddress) throws NodeServiceException;

	/**
	 * Updates the node with the given IP address.
	 *
	 * @param ipAddress The IPv4 address of the node to update.
	 * @param node      The node to persist.
	 * @throws NodeServiceException If there is an exception while updating the node.
	 */
	@PutMapping("/{ipAddress}")
	void updateNode(@PathVariable String ipAddress, @RequestBody NodeModel node) throws NodeServiceException;

}
