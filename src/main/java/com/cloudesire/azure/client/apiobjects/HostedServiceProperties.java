package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement ( name = "HostedServiceProperties" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class HostedServiceProperties
{
    @XmlElement ( name = "Description" )
    private String description;

    @XmlElement ( name = "AffinityGroup" )
    private String affinityGroup;

    @XmlElement ( name = "Location" )
    private String location;

    @XmlElement ( name = "Label" )
    private String label;

    @XmlElement ( name = "Status" )
    private String status;

    @XmlElement ( name = "DateCreated" )
    private Date dateCreated;

    @XmlElement ( name = "DateLastModified" )
    private Date dateLastModified;

    @XmlElement ( name = "ReverseDnsFqdn" )
    private String reverseDnsFqdn;

    @XmlElement ( name = "ExtendedProperties" )
    private List<ExtendedProperty> extendedProperties = new LinkedList<>();

    public List<ExtendedProperty> getExtendedProperties()
    {
        return extendedProperties;
    }

    public void setExtendedProperties( List<ExtendedProperty> extendedProperties )
    {
        this.extendedProperties = extendedProperties;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getAffinityGroup()
    {
        return affinityGroup;
    }

    public void setAffinityGroup( String affinityGroup )
    {
        this.affinityGroup = affinityGroup;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation( String location )
    {
        this.location = location;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated( Date dateCreated )
    {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified()
    {
        return dateLastModified;
    }

    public void setDateLastModified( Date dateLastModified )
    {
        this.dateLastModified = dateLastModified;
    }

    public String getReverseDnsFqdn()
    {
        return reverseDnsFqdn;
    }

    public void setReverseDnsFqdn( String reverseDnsFqdn )
    {
        this.reverseDnsFqdn = reverseDnsFqdn;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( ", extendedProperties=" );
        for ( ExtendedProperty ep : extendedProperties ) sb.append( ep );
        return "HostedServiceProperties{" +
                "description='" + description + '\'' +
                ", affinityGroup='" + affinityGroup + '\'' +
                ", location='" + location + '\'' +
                ", label='" + label + '\'' +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", reverseDnsFqdn='" + reverseDnsFqdn + '\'' +
                sb.toString() +
                '}';
    }
}
