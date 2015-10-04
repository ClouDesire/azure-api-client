package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.Operation;
import com.cloudesire.azure.client.apiobjects.enums.Status;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.azure.client.exceptions.TimeoutException;
import com.cloudesire.tisana4j.RestClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OperationClientImpl implements OperationClient
{
	private final URL operationsEndpoint;
	private final RestClient restClient;

	public OperationClientImpl( URL endpoint, RestClient restClient ) throws MalformedURLException
	{
		this.restClient = restClient;
		this.operationsEndpoint = new URL(endpoint, "operations/");
	}

	@Override
	public Status OperationStatus( String requestId ) throws Exception
	{
		return Status.valueOf(
				getStatus( requestId ).getStatus().toUpperCase()
		);
	}

	@Override
	public Operation getStatus( String requestId ) throws Exception
	{
		return
			OperationClientImpl.this.restClient.get(
					new URL(
							OperationClientImpl.this.operationsEndpoint, requestId
					), Operation.class
			);
	}

	@Override
	public void waitForCompletionOrFail( String requestId, Integer timeout, TimeUnit measuringUnit ) throws Exception
	{
		long start = System.currentTimeMillis();
		Operation operationStatus;
		do
		{
			if ( start + measuringUnit.toMillis( timeout ) < System.currentTimeMillis() ) throw new TimeoutException(
					String.format( "Timeout while waiting for request id %s to exit in progress status", requestId ) );
			Thread.sleep( 5000L );
			operationStatus = getStatus( requestId );
			if ( operationStatus.getStatus().toUpperCase().equals( Status.FAILED.toString() ) )
				throw new AzureClientException(
						operationStatus.getErrorResponse().getCode() + " " + operationStatus.getErrorResponse()
								.getMessage() );
		} while ( operationStatus.getStatus().toUpperCase().equals( Status.INPROGRESS.toString() ) );
		return;
	}

	@Override
	public void waitForState(
			String requestId, Status status, Integer timeout, TimeUnit measuringUnit ) throws Exception
	{
		Status realStatus;
		long start = System.currentTimeMillis();
		do
		{
			if ( start + measuringUnit.toMillis(timeout) < System.currentTimeMillis() ) throw new TimeoutException(
					String.format(
							"Timeout while waiting for request id %s to reach %s", requestId, status.name()
					)
			);
			Thread.sleep(5000L);
			realStatus = OperationClientImpl.this.OperationStatus(requestId);
		} while ( ! realStatus.equals(status) );
	}
}
