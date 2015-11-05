package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.Operation;
import com.cloudesire.azure.client.apiobjects.enums.Status;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.azure.client.exceptions.TimeoutException;
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public interface OperationClient
{
    Status operationStatus( String requestId ) throws MalformedURLException, RestException, AzureClientException;

    Operation getStatus( String requestId ) throws MalformedURLException, RestException, AzureClientException;

    void waitForCompletionOrFail( String requestId, Integer timeout, TimeUnit measuringUnit )
            throws MalformedURLException, RestException, TimeoutException, AzureClientException;

    @Deprecated
    void waitForState( String requestId, Status status, Integer timeout, TimeUnit measuringUnit )
            throws MalformedURLException, RestException, TimeoutException, AzureClientException;
}
