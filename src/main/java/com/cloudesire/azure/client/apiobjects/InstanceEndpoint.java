package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "InstanceEndpoint")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class InstanceEndpoint
{
	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "Vip")
	private String vip;

	@XmlElement (name = "PublicPort")
	private int publicPort;

	@XmlElement (name = "LocalPort")
	private int localPort;

	@XmlElement (name = "Protocol")
	private String protocol;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getVip()
	{
		return vip;
	}

	public void setVip( String vip )
	{
		this.vip = vip;
	}

	public int getPublicPort()
	{
		return publicPort;
	}

	public void setPublicPort( int publicPort )
	{
		this.publicPort = publicPort;
	}

	public int getLocalPort()
	{
		return localPort;
	}

	public void setLocalPort( int localPort )
	{
		this.localPort = localPort;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol( String protocol )
	{
		this.protocol = protocol;
	}
}
