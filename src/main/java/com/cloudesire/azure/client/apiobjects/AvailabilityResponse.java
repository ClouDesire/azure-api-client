package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlAccessorType (value = XmlAccessType.FIELD)
@XmlRootElement (name = "AvailabilityResponse")
public class AvailabilityResponse
{
	@XmlElement (name = "Result")
	private String result;

	@XmlElement (name = "Reason")
	private String reason;

	public String getResult ()
	{
		return result;
	}

	public void setResult ( String result )
	{
		this.result = result;
	}

	public String getReason ()
	{
		return reason;
	}

	public void setReason ( String reason )
	{
		this.reason = reason;
	}
}
