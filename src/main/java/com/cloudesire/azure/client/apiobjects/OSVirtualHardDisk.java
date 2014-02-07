package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlAccessorType ( value = XmlAccessType.FIELD )
@XmlType ( name = "OSVirtualHardDisk" )
public class OSVirtualHardDisk
{
	@XmlElement ( name = "HostCaching" )
	private String hostCaching;

	@XmlElement ( name = "DiskLabel" )
	private String diskLabel;

	@XmlElement ( name = "DiskName" )
	private String diskName;

	@XmlElement ( name = "MediaLink" )
	private String mediaLink;

	@XmlElement ( name = "SourceImageName" )
	private String sourceImageName;

	public String getDiskLabel ()
	{
		return diskLabel;
	}

	public void setDiskLabel ( String diskLabel )
	{
		this.diskLabel = diskLabel;
	}

	public String getHostCaching ()
	{
		return hostCaching;
	}

	public void setHostCaching ( String hostCaching )
	{
		this.hostCaching = hostCaching;
	}

	public String getDiskName ()
	{
		return diskName;
	}

	public void setDiskName ( String diskName )
	{
		this.diskName = diskName;
	}

	public String getMediaLink ()
	{
		return mediaLink;
	}

	public void setMediaLink ( String mediaLink )
	{
		this.mediaLink = mediaLink;
	}

	public String getSourceImageName ()
	{
		return sourceImageName;
	}

	public void setSourceImageName ( String sourceImageName )
	{
		this.sourceImageName = sourceImageName;
	}

	@Override
	public String toString ()
	{
		return " HostCachine: " + hostCaching + " DiskLabel: " + diskLabel + " DiskName: " + diskLabel + " MediaLink: " + mediaLink + " SourceImageName: " + sourceImageName;
	}
}
