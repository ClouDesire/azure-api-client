package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType ( name = "WinRM" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class WinRM
{

    public WinRM()
    {

    }

    public WinRM( Listener listener )
    {
        listeners.add( listener );
    }

    @XmlElementWrapper ( name = "Listeners" )
    @XmlElement ( name = "Listener", type = Listener.class )
    private List<Listener> listeners = new ArrayList<>();

    public List<Listener> getListeners()
    {
        return listeners;
    }

    public void setListeners( List<Listener> listeners )
    {
        this.listeners = listeners;
    }
}
