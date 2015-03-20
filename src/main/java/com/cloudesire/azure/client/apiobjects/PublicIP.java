package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.*;


@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "PublicIP")
public class PublicIP
{
	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "IdleTimeoutInMinutes")
	private String idleTimeoutInMinutes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdleTimeoutInMinutes() {
		return idleTimeoutInMinutes;
	}

	public void setIdleTimeoutInMinutes(String idleTimeoutInMinutes) {
		this.idleTimeoutInMinutes = idleTimeoutInMinutes;
	}
}