package com.cloudesire.azure.client.apiobjects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@JacksonXmlRootElement ( localName = "Images", namespace = "http://schemas.microsoft.com/windowsazure" )
public class Images
{
	@JacksonXmlProperty ( localName = "OSImage")
	@JacksonXmlElementWrapper ( useWrapping = false )
	private List<OSImage> osImages;

	public List<OSImage> getOsImages ()
	{
		return osImages;
	}

	public void setOsImages ( List<OSImage> osImages )
	{
		this.osImages = osImages;
	}
}
