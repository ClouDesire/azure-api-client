package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.Operation;
import com.cloudesire.azure.client.apiobjects.enums.Status;
import com.cloudesire.tisana4j.RestClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public class OperationClientImpl implements OperationClient
{
	private final URL operationsEndpoint;
	private final RestClient restClient;

	public OperationClientImpl ( URL endpoint, RestClient restClient ) throws MalformedURLException
	{
		this.restClient = restClient;
		this.operationsEndpoint = new URL(endpoint, "operations/");
	}

	@Override
	public Status OperationStatus ( String requestId ) throws Exception
	{
		return Status.valueOf(
				OperationClientImpl.this.restClient.get(
						new URL(
								OperationClientImpl.this.operationsEndpoint, requestId
						), Operation.class
				).getStatus().toUpperCase()
		);
	}
}
