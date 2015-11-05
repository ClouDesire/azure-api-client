package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "HostedService" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class HostedService
{
    @XmlElement ( name = "Url" )
    private String url;

    @XmlElement ( name = "ServiceName" )
    private String serviceName;

    @XmlElement ( name = "HostedServiceProperties" )
    private HostedServiceProperties hostedServiceProperties;

    @XmlElement ( name = "DefaultWinRMCertificateThumbprint" )
    private String defaultWinRMCertificateThumbprint;

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName( String serviceName )
    {
        this.serviceName = serviceName;
    }

    public HostedServiceProperties getHostedServiceProperties()
    {
        return hostedServiceProperties;
    }

    public void setHostedServiceProperties( HostedServiceProperties hostedServiceProperties )
    {
        this.hostedServiceProperties = hostedServiceProperties;
    }

    public String getDefaultWinRMCertificateThumbprint()
    {
        return defaultWinRMCertificateThumbprint;
    }

    public void setDefaultWinRMCertificateThumbprint( String defaultWinRMCertificateThumbprint )
    {
        this.defaultWinRMCertificateThumbprint = defaultWinRMCertificateThumbprint;
    }

    @Override
    public String toString()
    {
        return "HostedService{" +
                "url='" + url + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", " + hostedServiceProperties +
                ", defaultWinRMCertificateThumbprint='" + defaultWinRMCertificateThumbprint + '\'' +
                '}';
    }
}
