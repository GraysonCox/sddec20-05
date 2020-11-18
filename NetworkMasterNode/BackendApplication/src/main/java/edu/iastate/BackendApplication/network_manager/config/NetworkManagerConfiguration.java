package edu.iastate.BackendApplication.network_manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iastate.BackendApplication.network_manager.controller.NodeController;
import edu.iastate.BackendApplication.network_manager.controller.NodeControllerImpl;
import edu.iastate.BackendApplication.network_manager.service.NmapClient;
import edu.iastate.BackendApplication.network_manager.service.NodeApiClient;
import edu.iastate.BackendApplication.network_manager.service.NodeService;
import edu.iastate.BackendApplication.network_manager.service.NodeServiceImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class for the network manager component.
 */
@Configuration
public class NetworkManagerConfiguration {

	@Bean
	public NetworkManagerConfigurationProperties networkManagerConfigurationProperties() {
		return new NetworkManagerConfigurationProperties();
	}

	@Bean
	public NodeController nodeController(NodeService nodeService) {
		return new NodeControllerImpl(nodeService);
	}

	@Bean
	public NodeService nodeService(NmapClient nmapClient,
								   NodeApiClient nodeApiClient) {
		return new NodeServiceImpl(nmapClient, nodeApiClient);
	}

	@Bean
	public NmapClient nmapClient(Runtime runtime,
								 NetworkManagerConfigurationProperties networkManagerConfigurationProperties) {
		return new NmapClient(runtime, networkManagerConfigurationProperties);
	}

	@Bean
	public NodeApiClient nodeApiClient(HttpClient httpClient, ObjectMapper objectMapper) {
		return new NodeApiClient(httpClient, objectMapper);
	}

	@Bean
	public Runtime runtime() {
		return Runtime.getRuntime();
	}

	@Bean
	public HttpClient httpClient(NetworkManagerConfigurationProperties networkManagerConfigurationProperties) {
		int nodeApiTimeout = (int) networkManagerConfigurationProperties.getNodeApiTimeout();
		RequestConfig config = RequestConfig.custom()
											.setConnectTimeout(nodeApiTimeout)
											.setConnectionRequestTimeout(nodeApiTimeout)
											.build();
		return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
