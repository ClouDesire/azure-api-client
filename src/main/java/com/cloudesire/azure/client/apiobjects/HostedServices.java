package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement ( name = "HostedServices" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class HostedServices
{
    @XmlElement ( name = "HostedService" )
    private List<HostedService> hostedServices = new LinkedList<>();

    public List<HostedService> getHostedServices()
    {
        return hostedServices;
    }

    public void setHostedServices( List<HostedService> hostedServices )
    {
        this.hostedServices = hostedServices;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( " HostedServices{" );
        for ( HostedService hs : hostedServices )
        {
            sb.append( hs );
        }
        sb.append( '}' );
        return sb.toString();
    }
}
