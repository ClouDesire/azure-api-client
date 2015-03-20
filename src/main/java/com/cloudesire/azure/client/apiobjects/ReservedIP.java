package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.*;

@XmlRootElement (name = "ReservedIP")
@XmlType (propOrder = {"name", "label", "location", "address"})
@XmlAccessorType (value = XmlAccessType.FIELD)
public class ReservedIP
{
	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "Label")
	private String label;

	@XmlElement (name = "Location")
	private String location;

	@XmlElement (name = "Address")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
