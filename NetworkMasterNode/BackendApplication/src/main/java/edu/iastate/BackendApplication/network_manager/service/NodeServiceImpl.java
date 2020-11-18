package edu.iastate.BackendApplication.network_manager.service;

import edu.iastate.BackendApplication.network_manager.exception.NmapClientException;
import edu.iastate.BackendApplication.network_manager.exception.NodeApiClientException;
import edu.iastate.BackendApplication.network_manager.exception.NodeServiceException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The default implementation of NodeService.
 */
public class NodeServiceImpl implements NodeService {

	private static final Logger logger = LoggerFactory.getLogger(NodeServiceImpl.class);

	private final NmapClient nmapClient;
	private final NodeApiClient nodeApiClient;

	public NodeServiceImpl(NmapClient nmapClient, NodeApiClient nodeApiClient) {
		this.nmapClient = nmapClient;
		this.nodeApiClient = nodeApiClient;
	}

	@Override
	public List<NodeModel> getAllNodes() throws NodeServiceException {
		logger.info("Finding all nodes in network...");
		try {
			List<String> ipAddresses = nmapClient.getIpAddressesInNetwork();
			List<NodeModel> nodes = new ArrayList<>(ipAddresses.size());
			for (String ipAddress : ipAddresses) {
				NodeModel node = nodeApiClient.getNodeByIpAddress(ipAddress);
				if (node != null) {
					nodes.add(node);
				}
			}
			return nodes;
		} catch (NmapClientException | NodeApiClientException e) {
			throw NodeServiceException.withMessage("There was an error while fetching all nodes.", e);
		}
	}

	@Override
	public NodeModel getNodeByIpAddress(String ipAddress) throws NodeServiceException {
		logger.info("Fetching node with IPv4 address {}...", ipAddress);
		if (!InetAddressValidator.getInstance().isValidInet4Address(ipAddress)) {
			throw NodeServiceException.withMessage("The address is not a valid IPv4 address.");
		}

		try {
			return nodeApiClient.getNodeByIpAddress(ipAddress);
		} catch (NodeApiClientException e) {
			throw NodeServiceException.withMessage("There was an error while fetching node at " + ipAddress, e);
		}
	}

	@Override
	public void updateNode(String ipAddress, NodeModel node) throws NodeServiceException {
		logger.info("Updating node with IPv4 address {}...", ipAddress);
		try {
			nodeApiClient.updateNode(node);
		} catch (NodeApiClientException e) {
			String message = "There was an error while fetching node at " + node.getIpAddress();
			throw NodeServiceException.withMessage(message, e);
		}
	}

}
