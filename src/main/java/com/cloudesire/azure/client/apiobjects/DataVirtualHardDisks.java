package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "DataVirtualHardDisks")
public class DataVirtualHardDisks
{
	@XmlElement (name = "DataVirtualHardDisk")
	private List<DataVirtualHardDisk> dataVirtualHardDisks;

	public List<DataVirtualHardDisk> getDataVirtualHardDisks()
	{
		return dataVirtualHardDisks;
	}

	public void setDataVirtualHardDisks( List<DataVirtualHardDisk> dataVirtualHardDisks )
	{
		this.dataVirtualHardDisks = dataVirtualHardDisks;
	}

	@Override
	public String toString()
	{
		String ret = " DataVirtualHardDisks: ";
		if ( dataVirtualHardDisks != null )
			for ( DataVirtualHardDisk dd : dataVirtualHardDisks )
			{
				ret += " DataVirtualHardDisk: " + dd;
			}
		return ret;
	}
}
