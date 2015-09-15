package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType (name = "InputEndpoint")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class InputEndpoint
{
	@XmlElement (name = "LocalPort")
	private int localPort;

	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "Port")
	private int port;

	@XmlElement (name = "Protocol")
	private String protocol;

	public int getLocalPort()
	{
		return localPort;
	}

	public void setLocalPort( int localPort )
	{
		this.localPort = localPort;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort( int port )
	{
		this.port = port;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol( String protocol )
	{
		this.protocol = protocol;
	}

	@Override
	public String toString()
	{
		return "LocalPort: " + localPort + " Name: " + name + " Protocol: " + protocol + " Port: " + port;
	}
}
