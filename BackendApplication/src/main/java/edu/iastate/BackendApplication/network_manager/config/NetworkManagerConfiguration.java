package edu.iastate.BackendApplication.network_manager.config;

import edu.iastate.BackendApplication.network_manager.controller.NodeController;
import edu.iastate.BackendApplication.network_manager.controller.NodeControllerImpl;
import edu.iastate.BackendApplication.network_manager.service.NodeService;
import edu.iastate.BackendApplication.network_manager.service.NodeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class for the network manager component.
 *
 * @author Grayson Cox
 */
@Configuration
public class NetworkManagerConfiguration {

	@Bean
	public NodeController nodeController(NodeService nodeService) {
		return new NodeControllerImpl(nodeService);
	}

	@Bean
	public NodeService nodeService() {
		return new NodeServiceImpl(); // TODO
	}

}
