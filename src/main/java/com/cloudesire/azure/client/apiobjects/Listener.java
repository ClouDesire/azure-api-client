package com.cloudesire.azure.client.apiobjects;

import com.cloudesire.azure.client.apiobjects.Deployment.Builder.WinRMProtocol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "Listener" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class Listener
{
    @XmlElement ( name = "CertificateThumbprint" )
    private String certificateThumbprint;

    @XmlElement ( name = "Protocol" )
    private String protocol;

    public Listener()
    {

    }

    public Listener( WinRMProtocol winRMProtocol )
    {
        this.protocol = winRMProtocol.toString();
    }

    public String getCertificateThumbprint()
    {
        return certificateThumbprint;
    }

    public void setCertificateThumbprint( String certificateThumbprint )
    {
        this.certificateThumbprint = certificateThumbprint;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol( String protocol )
    {
        this.protocol = protocol;
    }

    @Override
    public String toString()
    {
        return "CertificateThumbprint: " + certificateThumbprint + " Protocol: " + protocol;
    }
}
