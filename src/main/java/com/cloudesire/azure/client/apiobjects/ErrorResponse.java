package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Error")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class ErrorResponse
{
	@XmlElement (name = "Code")
	private String code;

	@XmlElement (name = "Message")
	private String message;

	public String getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setCode( String code )
	{
		this.code = code;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}
}
