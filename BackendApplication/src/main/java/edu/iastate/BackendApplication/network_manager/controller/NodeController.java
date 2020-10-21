package edu.iastate.BackendApplication.network_manager.controller;

import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provides a REST API for viewing active nodes in the network.
 *
 * @author Grayson Cox
 */
@RestController
@RequestMapping("node")
public interface NodeController {

	/**
	 * Returns a list containing all the active nodes in the network.
	 *
	 * @return A list containing all the active nodes in the network.
	 * @throws Exception If there is an exception while fetching all the nodes in the network.
	 */
	@CrossOrigin
	 @GetMapping("/all")
	List<NodeModel> getAllNodes() throws Exception;

	/**
	 * Returns the node with the given ID, or null if the node does not exist.
	 *
	 * @param nodeId The node ID.
	 * @return The node with the given ID, or null if the node does not exist.
	 * @throws Exception If there is an exception while fetching the desired node.
	 */
	@CrossOrigin
	@GetMapping("/{nodeId}")
	NodeModel getNodeById(@PathVariable Long nodeId) throws Exception;

}
