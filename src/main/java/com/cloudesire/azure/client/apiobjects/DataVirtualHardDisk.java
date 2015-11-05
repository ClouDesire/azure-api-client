package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType ( value = XmlAccessType.FIELD )
@XmlType ( name = "DataVirtualHardDisk",
        propOrder = { "hostCaching", "diskLabel", "diskName", "lun", "logicalDiskSizeInGB", "mediaLink",
                "sourceImageName" } )
@XmlRootElement ( name = "DataVirtualHardDisk" )
public class DataVirtualHardDisk extends Disk
{
    @XmlElement ( name = "Lun" )
    private Integer lun;

    @XmlElement ( name = "LogicalDiskSizeInGB" )
    private int logicalDiskSizeInGB;

    public Integer getLun()
    {
        return lun;
    }

    public void setLun( Integer lun )
    {
        this.lun = lun;
    }

    public int getLogicalDiskSizeInGB()
    {
        return logicalDiskSizeInGB;
    }

    public void setLogicalDiskSizeInGB( int logicalDiskSizeInGB )
    {
        this.logicalDiskSizeInGB = logicalDiskSizeInGB;
    }
}
