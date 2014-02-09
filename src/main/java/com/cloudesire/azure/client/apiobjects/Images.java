package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement ( name = "Images" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class Images
{
	@XmlElement ( name = "OSImage" )
	private List<OSImage> osImages;

	public List<OSImage> getOsImages ()
	{
		return osImages;
	}

	public void setOsImages ( List<OSImage> osImages )
	{
		this.osImages = osImages;
	}

	@Override
	public String toString ()
	{
		String ret = " Images: ";
		for (OSImage image : osImages)
		{
			ret += " OSImage: " + image;
		}
		return ret;
	}
}
