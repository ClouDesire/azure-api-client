package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.DataVirtualHardDisk;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.ExtensionImage;
import com.cloudesire.azure.client.apiobjects.HostedService;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.ReservedIP;
import com.cloudesire.azure.client.apiobjects.ResourceExtension;
import com.cloudesire.azure.client.apiobjects.StorageService;
import com.cloudesire.azure.client.apiobjects.enums.DeploymentSlot;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.util.List;

public interface ServiceClient
{
    OSImage findImage( String name ) throws MalformedURLException, RestException, AzureClientException;

    List<OSImage> listImages() throws Exception;

    Deployment getDeployment( String serviceName, String deploymentName )
            throws MalformedURLException, RestException, AzureClientException;

    Deployment getDeploymentBySlot( String serviceName, DeploymentSlot deploymentSlot )
            throws MalformedURLException, RestException, AzureClientException;

    String createCloudService( CloudService cloudService )
            throws MalformedURLException, RestException, AzureClientException;

    String createStorageService( StorageService cloudService )
            throws MalformedURLException, RestException, AzureClientException;

    void deleteStorageService( String storageServiceName )
            throws MalformedURLException, RestException, AzureClientException;

    List<StorageService> listStorageServices() throws MalformedURLException, RestException, AzureClientException;

    List<ExtensionImage> listExtensionImages() throws MalformedURLException, RestException, AzureClientException;

    List<ResourceExtension> listResourceExtensions() throws MalformedURLException, RestException, AzureClientException;

    List<HostedService> listCloudServices() throws MalformedURLException, RestException, AzureClientException;

    HostedService getCloudService( String serviceName )
            throws MalformedURLException, RestException, AzureClientException;

    String addServiceCertificate( String data, String format, String password, String serviceName )
            throws MalformedURLException, RestException, AzureClientException;

    String createDeployment( Deployment deployment, String serviceName )
            throws MalformedURLException, RestException, AzureClientException;

    String destroyCloudService( String serviceName, boolean cascadeDelete )
            throws MalformedURLException, RestException, AzureClientException;

    String resumeMachine( String serviceName, String deploymentName, String roleName )
            throws MalformedURLException, RestException, AzureClientException;

    String stopMachine( String serviceName, String deploymentName, String roleName )
            throws MalformedURLException, RestException, AzureClientException;

    Boolean isCloudServiceAvailable( String serviceName )
            throws MalformedURLException, RestException, AzureClientException;

    String addDataDisk( String serviceName, String deploymentName, String roleName, DataVirtualHardDisk disk )
            throws MalformedURLException, RestException, AzureClientException;

    String createReservedIP( ReservedIP reservedIP ) throws MalformedURLException, RestException, AzureClientException;

    ReservedIP getReservedIP( String name ) throws MalformedURLException, RestException, AzureClientException;

    List<ReservedIP> getReservedIPs() throws MalformedURLException, RestException, AzureClientException;

    void deleteReservedIP( String name ) throws MalformedURLException, RestException, AzureClientException;

    Boolean reservedIPExists( String name ) throws MalformedURLException, RestException, AzureClientException;
}
