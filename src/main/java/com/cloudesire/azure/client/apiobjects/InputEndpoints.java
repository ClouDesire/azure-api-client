package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

@XmlType (name = "InputEndpoints")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class InputEndpoints
{
	@XmlElement (name = "InputEndpoint")
	private List<InputEndpoint> endpoints = new LinkedList<>(  );

	public List<InputEndpoint> getEndpoints()
	{
		return endpoints;
	}

	public void setEndpoints( List<InputEndpoint> endpoints )
	{
		this.endpoints = endpoints;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder( "InputEndpoints{ " );
		for (InputEndpoint ie : endpoints) sb.append( ie + " " );
		return "InputEndpoints{" +
				 sb.toString() +
				" }";
	}
}
