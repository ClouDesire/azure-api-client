package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "InputEndpoints")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class InputEndpoints
{
	@XmlElement (name = "InputEndpoint")
	private List<InputEndpoint> endpoints;

	public List<InputEndpoint> getEndpoints()
	{
		return endpoints;
	}

	public void setEndpoints( List<InputEndpoint> endpoints )
	{
		this.endpoints = endpoints;
	}
}
