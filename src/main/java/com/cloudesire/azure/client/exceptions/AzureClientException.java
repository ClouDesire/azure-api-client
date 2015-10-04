package com.cloudesire.azure.client.exceptions;

public class AzureClientException extends Exception
{

	private static final long serialVersionUID = -1584547674939070605L;

	public AzureClientException( String message )
	{
		super(message);
	}

	public AzureClientException( String message, Exception e )
	{
		super(message, e);
	}

}
