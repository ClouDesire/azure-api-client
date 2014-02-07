package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement ( name = "Locations" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class Locations
{
	@XmlElement (name = "Location")
	private List<Location> locations;

	public List<Location> getLocations ()
	{
		return locations;
	}

	public void setLocations ( List<Location> locations )
	{
		this.locations = locations;
	}
}
