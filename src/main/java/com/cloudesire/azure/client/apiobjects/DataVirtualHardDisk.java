package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlAccessorType (value = XmlAccessType.FIELD)
@XmlType (name = "DataVirtualHardDisk")
public class DataVirtualHardDisk extends Disk
{
	@XmlElement (name = "Lun")
	private int lun;

	@XmlElement (name = "LogicalDiskSizeInGB")
	private int logicalDiskSizeInGB;

	public int getLun ()
	{
		return lun;
	}

	public void setLun ( int lun )
	{
		this.lun = lun;
	}

	public int getLogicalDiskSizeInGB ()
	{
		return logicalDiskSizeInGB;
	}

	public void setLogicalDiskSizeInGB ( int logicalDiskSizeInGB )
	{
		this.logicalDiskSizeInGB = logicalDiskSizeInGB;
	}

	@Override
	public String toString ()
	{
		return " HostCachine: " + hostCaching + " DiskLabel: " + diskLabel + " DiskName: " + diskLabel + " MediaLink: " + mediaLink + " SourceImageName: " + sourceImageName;
	}
}
