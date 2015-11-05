package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType ( XmlAccessType.FIELD )
@XmlTransient
public class Disk
{
    @XmlElement ( name = "HostCaching" )
    protected String hostCaching;

    @XmlElement ( name = "DiskLabel" )
    protected String diskLabel;

    @XmlElement ( name = "DiskName" )
    protected String diskName;

    @XmlElement ( name = "MediaLink" )
    protected String mediaLink;

    @XmlElement ( name = "SourceImageName" )
    protected String sourceImageName;

    public String getDiskLabel()
    {
        return diskLabel;
    }

    public void setDiskLabel( String diskLabel )
    {
        this.diskLabel = diskLabel;
    }

    public String getHostCaching()
    {
        return hostCaching;
    }

    public void setHostCaching( String hostCaching )
    {
        this.hostCaching = hostCaching;
    }

    public String getDiskName()
    {
        return diskName;
    }

    public void setDiskName( String diskName )
    {
        this.diskName = diskName;
    }

    public String getMediaLink()
    {
        return mediaLink;
    }

    public void setMediaLink( String mediaLink )
    {
        this.mediaLink = mediaLink;
    }

    public String getSourceImageName()
    {
        return sourceImageName;
    }

    public void setSourceImageName( String sourceImageName )
    {
        this.sourceImageName = sourceImageName;
    }

    @Override
    public String toString()
    {
        return " HostCachine: " + hostCaching + " DiskLabel: " + diskLabel + " DiskName: " + diskLabel + " MediaLink: "
                + mediaLink + " SourceImageName: " + sourceImageName;
    }
}
