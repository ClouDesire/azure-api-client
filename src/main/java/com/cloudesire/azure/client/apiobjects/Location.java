package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

@XmlRootElement ( name = "Location" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class Location
{
    @XmlElement ( name = "Name" )
    private String name;

    @XmlElement ( name = "DisplayName" )
    private String displayName;

    @XmlElement ( name = "AvailableServices" )
    private List<String> avaiableServices;

    public String getName()
    {
        return name;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public List<String> getAvaiableServices()
    {
        return avaiableServices;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + " DisplayName: " + displayName + " AvailableServices: " + Arrays
                .toString( avaiableServices.toArray( new String[0] ) );
    }
}
