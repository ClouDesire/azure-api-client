package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.Operation;
import com.cloudesire.azure.client.apiobjects.enums.Status;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.azure.client.exceptions.TimeoutException;
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OperationClientImpl implements OperationClient
{
    private final URL operationsEndpoint;
    private final AzureClient restClient;

    public OperationClientImpl( URL endpoint, AzureClient restClient ) throws MalformedURLException
    {
        this.restClient = restClient;
        this.operationsEndpoint = new URL( endpoint, "operations/" );
    }

    @Override
    public Status operationStatus( String requestId ) throws MalformedURLException, RestException, AzureClientException
    {
        return Status.valueOf( getStatus( requestId ).getStatus().toUpperCase() );
    }

    @Override
    public Operation getStatus( String requestId ) throws MalformedURLException, RestException, AzureClientException
    {
        return this.restClient.getClient()
                .get( new URL( OperationClientImpl.this.operationsEndpoint, requestId ), Operation.class );
    }

    @Override
    public void waitForCompletionOrFail( String requestId, Integer timeout, TimeUnit measuringUnit )
            throws MalformedURLException, RestException, TimeoutException, AzureClientException
    {
        long start = System.currentTimeMillis();
        Operation operationStatus;
        do
        {
            if ( start + measuringUnit.toMillis( timeout ) < System.currentTimeMillis() ) throw new TimeoutException(
                    String.format( "Timeout while waiting for request id %s to exit in progress status", requestId ) );
            try
            {
                Thread.sleep( 5000L );
            }
            catch ( InterruptedException e )
            {
                throw new AzureClientException( e );
            }
            operationStatus = getStatus( requestId );
            if ( operationStatus.getStatus().toUpperCase().equals( Status.FAILED.toString() ) )
                throw new AzureClientException(
                        operationStatus.getErrorResponse().getCode() + " " + operationStatus.getErrorResponse()
                                .getMessage() );
        } while ( operationStatus.getStatus().toUpperCase().equals( Status.INPROGRESS.toString() ) );
    }

    @Override
    public void waitForState( String requestId, Status status, Integer timeout, TimeUnit measuringUnit )
            throws MalformedURLException, RestException, TimeoutException, AzureClientException
    {
        Status realStatus;
        long start = System.currentTimeMillis();
        do
        {
            if ( start + measuringUnit.toMillis( timeout ) < System.currentTimeMillis() ) throw new TimeoutException(
                    String.format( "Timeout while waiting for request id %s to reach %s", requestId, status.name() ) );
            try
            {
                Thread.sleep( 5000L );
            }
            catch ( InterruptedException e )
            {
                throw new AzureClientException( e );
            }
            realStatus = operationStatus( requestId );
        } while ( !realStatus.equals( status ) );
    }
}
