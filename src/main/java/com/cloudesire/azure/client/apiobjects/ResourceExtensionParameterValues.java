package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType ( value = XmlAccessType.FIELD )
@XmlType ()
@XmlRootElement ( name = "ResourceExtensionParameterValues" )
public class ResourceExtensionParameterValues
{
    @XmlElement ( name = "ResourceExtensionParameterValue" )
    private List<ResourceExtensionParameterValue> resourceExtensionParameterValues;

    public List<ResourceExtensionParameterValue> getResourceExtensionParameterValues()
    {
        return resourceExtensionParameterValues;
    }

    public void setResourceExtensionParameterValues(
            List<ResourceExtensionParameterValue> resourceExtensionParameterValues )
    {
        this.resourceExtensionParameterValues = resourceExtensionParameterValues;
    }

}
