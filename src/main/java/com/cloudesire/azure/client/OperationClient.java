package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.Operation;
import com.cloudesire.azure.client.apiobjects.enums.Status;

import java.util.concurrent.TimeUnit;

public interface OperationClient
{
	Status OperationStatus( String requestId ) throws Exception;

	Operation getStatus( String requestId ) throws Exception;

	void waitForCompletionOrFail( String requestId, Integer timeout, TimeUnit measuringUnit ) throws Exception;

	@Deprecated
	void waitForState( String requestId, Status status, Integer timeout, TimeUnit measuringUnit ) throws Exception;
}
