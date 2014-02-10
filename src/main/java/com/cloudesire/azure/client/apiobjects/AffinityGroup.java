package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement (name = "CreateAffinityGroup")
@XmlType ()
@XmlAccessorType (value = XmlAccessType.FIELD)
public class AffinityGroup
{
	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "Label")
	private String label;

	@XmlElement (name = "Description")
	private String description;

	@XmlElement (name = "Location")
	private String location;

	public String getName ()
	{
		return name;
	}

	public void setName ( String name )
	{
		this.name = name;
	}

	public String getLabel ()
	{
		return label;
	}

	public void setLabel ( String label )
	{
		this.label = label;
	}

	public String getDescription ()
	{
		return description;
	}

	public void setDescription ( String description )
	{
		this.description = description;
	}

	public String getLocation ()
	{
		return location;
	}

	public void setLocation ( String location )
	{
		this.location = location;
	}
}
