package com.cloudesire.azure.client;

import com.cloudesire.tisana4j.RestClient;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class BaseClientImpl
{
    private static final String XMSID = "x-ms-request-id";

    protected final URL endpoint;
    protected final AzureClient restClient;

    public BaseClientImpl( URL endpoint, final AzureClient restClient ) throws MalformedURLException
    {
        this.restClient = restClient;
        if ( StringUtils.isNotBlank(getEndpoint()))
            this.endpoint = new URL( endpoint, getEndpoint() );
        else
            this.endpoint = endpoint;
    }

    protected abstract String getEndpoint();

    protected String getOperationId( RestClient restClient )
    {
        Map<String, List<String>> responseHeaders = restClient.getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }
}
