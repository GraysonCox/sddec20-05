package edu.iastate.BackendApplication.network_manager.controller;

import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import org.springframework.web.bind.annotation.*;

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
	@GetMapping("/all")
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
	 * Matches the given node with an existing node by its IP address and updates the existing node with the properties
	 * of the given node.
	 *
	 * @param node The node to persist.
	 * @throws NodeServiceException If there is an exception while updating the node.
	 */
	@PostMapping("/update")
	void updateNode(@RequestBody NodeModel node) throws NodeServiceException;

}
