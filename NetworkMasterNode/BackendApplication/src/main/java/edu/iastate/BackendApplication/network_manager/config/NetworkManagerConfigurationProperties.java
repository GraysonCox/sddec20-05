package edu.iastate.BackendApplication.network_manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "network.manager")
public class NetworkManagerConfigurationProperties {

	private String networkRange;

	private long networkScanTimeout;

	private long nodeApiTimeout;

	public String getNetworkRange() {
		return networkRange;
	}

	public void setNetworkRange(String networkRange) {
		this.networkRange = networkRange;
	}

	public long getNetworkScanTimeout() {
		return networkScanTimeout;
	}

	public void setNetworkScanTimeout(long networkScanTimeout) {
		this.networkScanTimeout = networkScanTimeout;
	}

	public long getNodeApiTimeout() {
		return nodeApiTimeout;
	}

	public void setNodeApiTimeout(long nodeApiTimeout) {
		this.nodeApiTimeout = nodeApiTimeout;
	}

}
