package edu.iastate.BackendApplication.network_manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iastate.BackendApplication.network_manager.exception.NodeApiClientException;
import edu.iastate.BackendApplication.network_manager.model.NodeModel;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A client class that interfaces with the REST API running on each of the Raspberry Pi's.
 */
public class NodeApiClient {

	private static final Logger logger = LoggerFactory.getLogger(NodeApiClient.class);

	private static final String GET_NODE_URL = "http://%s:5000/node";
	private static final String UPDATE_NODE_URL = "http://%s:5000/node/update";

	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public NodeApiClient(HttpClient httpClient, ObjectMapper objectMapper) {
		this.httpClient = httpClient;
		this.objectMapper = objectMapper;
	}

	/**
	 * Fetches the properties of the node at the given IPv4 address.
	 *
	 * @param ipAddress An IPv4 address.
	 * @return A JSON string containing the properties of the desired node.
	 * @throws NodeApiClientException If there is an exception while fetching the node.
	 */
	public NodeModel getNodeByIpAddress(String ipAddress) throws NodeApiClientException {
		logger.info("Fetching node at {}...", ipAddress);
		String url = String.format(GET_NODE_URL, ipAddress);
		HttpResponse response;

		try {
			response = httpClient.execute(new HttpGet(url));
		} catch (IOException e) {
			logger.warn("Could not connect to node at {}.", ipAddress);
			return null;
		}

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw NodeApiClientException.withHttpStatusCode(statusCode);
		}

		try {
			String resultString = EntityUtils.toString(response.getEntity());
			return objectMapper.readValue(resultString, NodeModel.class);
		} catch (IOException e) {
			throw NodeApiClientException.from(e);
		}
	}

	/**
	 * Finds the node with a matching IP address and updates its properties to match the given node model.
	 *
	 * @param node The new node model.
	 * @throws NodeApiClientException If there is an exception while updating the node.
	 */
	public void updateNode(NodeModel node) throws NodeApiClientException {
		logger.info("Updating node at {}...", node.getIpAddress());
		if (!InetAddressValidator.getInstance().isValidInet4Address(node.getIpAddress())) {
			throw NodeApiClientException.withMessage("The address is not a valid IPv4 address.");
		}
		if (node.getName() != null && node.getName().length() > 20) {
			throw NodeApiClientException.withMessage("The node name must contain 20 characters or fewer.");
		}
		if (node.getNetworkName().length() > 20) {
			throw NodeApiClientException.withMessage("The network name must contain 20 characters or fewer.");
		}

		HttpResponse response;

		try {
			String url = String.format(UPDATE_NODE_URL, node.getIpAddress());
			String body = objectMapper.writeValueAsString(node);

			HttpPost request = new HttpPost(url);
			request.setEntity(new StringEntity(body));
			request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

			response = httpClient.execute(request);
		} catch (IOException e) {
			throw NodeApiClientException.from(e);
		}

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw NodeApiClientException.withHttpStatusCode(statusCode);
		}
	}

}
