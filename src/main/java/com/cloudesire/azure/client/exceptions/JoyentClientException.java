package com.cloudesire.azure.client.exceptions;

public class JoyentClientException extends Exception
{

	private static final long serialVersionUID = -1584547674939070605L;

	public JoyentClientException(String message)
	{
		super(message);
	}

	public JoyentClientException(String message, Exception e)
	{
		super(message, e);
	}

}
