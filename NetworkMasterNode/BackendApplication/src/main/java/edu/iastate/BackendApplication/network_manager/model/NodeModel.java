package edu.iastate.BackendApplication.network_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a network node.
 */
public class NodeModel {

	@JsonProperty(value = "ipAddress", required = true)
	private String ipAddress;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private NodeType type;

	@JsonProperty("networkName")
	private String networkName;

	@JsonProperty("batteryPercentage")
	private Integer batteryPercentage;

	public NodeModel() {
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public Integer getBatteryPercentage() {
		return batteryPercentage;
	}

	public void setBatteryPercentage(Integer batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}

}
