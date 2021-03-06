package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "CreateStorageServiceInput" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class StorageService
{
    /**
     * Storage account names must be between 3 and 24 characters in length
     * and use numbers and lower-case letters only.
     */
    @XmlElement ( name = "ServiceName" )
    private String serviceName;

    @XmlElement ( name = "Label" )
    private String label;

    @XmlElement ( name = "Description" )
    private String description;

    @XmlElement ( name = "Location" )
    private String location;

    @XmlElement ( name = "AffinityGroup" )
    private String affinityGroup;

    @XmlElement ( name = "Value" )
    private boolean value;

    @XmlElement ( name = "AccountType" )
    private String accountType = "Standard_LRS";

    @XmlElement ( name = "Name" )
    private boolean name;

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName( String serviceName )
    {
        this.serviceName = serviceName;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation( String location )
    {
        this.location = location;
    }

    public String getAffinityGroup()
    {
        return affinityGroup;
    }

    public void setAffinityGroup( String affinityGroup )
    {
        this.affinityGroup = affinityGroup;
    }

    public boolean isValue()
    {
        return value;
    }

    public void setValue( boolean value )
    {
        this.value = value;
    }

    public boolean isName()
    {
        return name;
    }

    public void setName( boolean name )
    {
        this.name = name;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType( String accountType )
    {
        this.accountType = accountType;
    }

    @Override
    public String toString()
    {
        return "ServiceName: " + serviceName + " Description: " + description + " Label: " + label + " Location: "
                + location + " AffinityGroup: " + affinityGroup;
    }
}
