package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement ( name = "ResourceExtensions" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class ResourceExtensions
{
    @XmlElement ( name = "ResourceExtension" )
    List<ResourceExtension> resourceExtensions;

    public List<ResourceExtension> getResourceExtensions()
    {
        return resourceExtensions;
    }

    public void setResourceExtensions( List<ResourceExtension> resourceExtensions )
    {
        this.resourceExtensions = resourceExtensions;
    }

}

