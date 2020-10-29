package edu.iastate.BackendApplication.network_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a network node.
 *
 * @author Grayson Cox
 */
public class NodeModel {

	@JsonProperty(value = "id", required = true)
	private Long id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("name")
	private String name;

	@JsonProperty("ipAddress")
	private String ipAddress;

	@JsonProperty("batteryPercentage")
	private Integer batteryPercentage;

	public NodeModel(Long id, String type, String name, String ipAddress, Integer batteryPercentage) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.ipAddress = ipAddress;
		this.batteryPercentage = batteryPercentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getBatteryPercentage() {
		return batteryPercentage;
	}

	public void setBatteryPercentage(Integer batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}

}
