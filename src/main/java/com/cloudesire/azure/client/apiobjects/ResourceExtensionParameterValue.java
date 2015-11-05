package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType ( value = XmlAccessType.FIELD )
@XmlType ( name = "ResourceExtensionParameterValue", propOrder = { "key", "value", "type" } )
@XmlRootElement ( name = "ResourceExtensionParameterValue" )
public class ResourceExtensionParameterValue
{
    @XmlElement ( name = "Key" )
    private String key;
    @XmlElement ( name = "Value" )
    private String value;
    @XmlElement ( name = "Type" )
    private String type; // Public | Private

    public String getKey()
    {
        return key;
    }

    public void setKey( String key )
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ResourceExtensionParameterValue [key=" + key + ", value=" + value + ", type=" + type + "]";
    }

}
