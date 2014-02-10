package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement (name = "Operation")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class Operation
{
	@XmlElement (name = "ID")
	private int requestId;

	@XmlElement (name = "Status")
	private String status;

	@XmlElement (name = "HttpStatusCode")
	private String httpStatusCode;

	@XmlElement (name = "Error")
	private ErrorResponse errorResponse;

	public int getRequestId ()
	{
		return requestId;
	}

	public void setRequestId ( int requestId )
	{
		this.requestId = requestId;
	}

	public String getStatus ()
	{
		return status;
	}

	public void setStatus ( String status )
	{
		this.status = status;
	}

	public String getHttpStatusCode ()
	{
		return httpStatusCode;
	}

	public void setHttpStatusCode ( String httpStatusCode )
	{
		this.httpStatusCode = httpStatusCode;
	}

	public ErrorResponse getErrorResponse ()
	{
		return errorResponse;
	}

	public void setErrorResponse ( ErrorResponse errorResponse )
	{
		this.errorResponse = errorResponse;
	}
}
