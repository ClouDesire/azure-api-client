package com.cloudesire.azure.client;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement (localName = "Error", namespace = "http://schemas.microsoft.com/windowsazure")
public class ErrorResponse
{
	@JacksonXmlProperty (localName = "Code")
	private String code;

	@JacksonXmlProperty (localName = "Message")
	private String message;

	public String getCode ()
	{
		return code;
	}

	public String getMessage ()
	{
		return message;
	}

	public void setCode ( String code )
	{
		this.code = code;
	}

	public void setMessage ( String message )
	{
		this.message = message;
	}

}
