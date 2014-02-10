package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.enums.Status;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public interface OperationClient
{
	Status OperationStatus ( String requestId ) throws Exception;
}
