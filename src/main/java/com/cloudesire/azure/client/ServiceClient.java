package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.DataVirtualHardDisk;
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

	String addServiceCertificate ( String data, String format, String password, String serviceName ) throws Exception;

	String createDeployment ( Deployment deployment, String serviceName ) throws Exception;

	String destroyCloudService ( String serviceName, boolean cascadeDelete ) throws Exception;

	String resumeMachine ( String serviceName, String deploymentName, String roleName ) throws Exception;

	String stopMachine ( String serviceName, String deploymentName, String roleName ) throws Exception;

	Boolean isCloudServiceAvailable ( String serviceName ) throws Exception;

	String addDataDisk (
			String serviceName, String deploymentName, String roleName, DataVirtualHardDisk disk ) throws Exception;
}
