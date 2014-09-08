package com.cloudesire.azure.client.apiobjects;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "ExtensionImages" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class ExtensionImages
{
	@XmlElement ( name = "ExtensionImage" )
	private List<ExtensionImage> extensionImages;

	public List<ExtensionImage> getExtensionImages ()
	{
		return extensionImages;
	}

	public void setExtensionImages ( List<ExtensionImage> extensionImages )
	{
		this.extensionImages = extensionImages;
	}

}
