package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.StorageService;

import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public interface ServiceClient
{
	OSImage findImage ( String name ) throws Exception;

	List<OSImage> listImages () throws Exception;

	Deployment getDeployment ( String serviceName, String deploymentName ) throws Exception;

	String createCloudService ( CloudService cloudService ) throws Exception;

	String createStorageService ( StorageService cloudService ) throws Exception;

	String createDeployment ( Deployment deployment, String serviceName ) throws Exception;
}
