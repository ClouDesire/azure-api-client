package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement (name = "CreateHostedService")
@XmlType (propOrder = {"serviceName", "label", "description", "location", "affinityGroup"})
@XmlAccessorType (value = XmlAccessType.FIELD)
public class CloudService
{
	@XmlElement (name = "ServiceName")
	private String serviceName;

	@XmlElement (name = "Label")
	private String label;

	@XmlElement (name = "Description")
	private String description;

	@XmlElement (name = "Location")
	private String location;

	@XmlElement (name = "AffinityGroup")
	private String affinityGroup;

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName( String serviceName )
	{
		this.serviceName = serviceName;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public String getAffinityGroup()
	{
		return affinityGroup;
	}

	public void setAffinityGroup( String affinityGroup )
	{
		this.affinityGroup = affinityGroup;
	}

	@Override
	public String toString()
	{
		return "ServiceName: " + serviceName + " Label: " + " Description: " + description + " Location: " + location + " AfiinityGroup: " + affinityGroup;
	}
}
