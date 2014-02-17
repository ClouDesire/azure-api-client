package com.cloudesire.azure.client;

public class AzureResponseException extends Exception
{
	public AzureResponseException( String code, String message )
	{
		super(code + ": " + message);
	}

}
