package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType ( value = XmlAccessType.FIELD )
@XmlType ()
@XmlRootElement ( name = "ResourceExtensionReferences" )
public class ResourceExtensionReferences
{
    @XmlElement ( name = "ResourceExtensionReference" )
    private List<ResourceExtensionReference> resourceExtensionReferences;

    public List<ResourceExtensionReference> getResourceExtensionReferences()
    {
        return resourceExtensionReferences;
    }

    public void setResourceExtensionReferences( List<ResourceExtensionReference> resourceExtensionReferences )
    {
        this.resourceExtensionReferences = resourceExtensionReferences;
    }

}
