package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement( name = "ReservedIPs" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class ReservedIPs
{
	@XmlElement (name = "ReservedIP")
	private List<ReservedIP> reservedIPs;

	public List<ReservedIP> getReservedIPs() {
		return reservedIPs;
	}

	public void setReservedIPs(List<ReservedIP> reservedIPs) {
		this.reservedIPs = reservedIPs;
	}
}
