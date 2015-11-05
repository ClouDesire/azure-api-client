package com.cloudesire.azure.client;

import com.cloudesire.tisana4j.exceptions.RestException;

public class AzureResponseException extends RestException
{
    private static final long serialVersionUID = -4908471987997531987L;

    public AzureResponseException( int code, String message )
    {
        super( code, message );
    }

}
