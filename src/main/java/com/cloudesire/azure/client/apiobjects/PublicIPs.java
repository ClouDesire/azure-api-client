package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement ( name = "PublicIPs" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class PublicIPs
{
    @XmlElement ( name = "PublicIP" )
    private List<PublicIP> publicIPs;

    public List<PublicIP> getPublicIPs()
    {
        return publicIPs;
    }

    public void setPublicIPs( List<PublicIP> publicIPs )
    {
        this.publicIPs = publicIPs;
    }
}
