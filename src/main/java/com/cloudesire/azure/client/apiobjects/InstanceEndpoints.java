package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "InstanceEndpoints")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class InstanceEndpoints
{
	@XmlElement (name = "InstanceEndpoint")
	private List<InstanceEndpoint> endpoints;

	public List<InstanceEndpoint> getEndpoints()
	{
		return endpoints;
	}

	public void setEndpoints( List<InstanceEndpoint> endpoints )
	{
		this.endpoints = endpoints;
	}
}
