package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AvailabilityResponse;
import com.cloudesire.azure.client.apiobjects.CertificateFile;
import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.DataVirtualHardDisk;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.ExtensionImage;
import com.cloudesire.azure.client.apiobjects.ExtensionImages;
import com.cloudesire.azure.client.apiobjects.HostedService;
import com.cloudesire.azure.client.apiobjects.HostedServices;
import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.ReservedIP;
import com.cloudesire.azure.client.apiobjects.ReservedIPs;
import com.cloudesire.azure.client.apiobjects.ResourceExtension;
import com.cloudesire.azure.client.apiobjects.ResourceExtensions;
import com.cloudesire.azure.client.apiobjects.ShutdownRoleOperation;
import com.cloudesire.azure.client.apiobjects.StartRoleOperation;
import com.cloudesire.azure.client.apiobjects.StorageService;
import com.cloudesire.azure.client.apiobjects.StorageServices;
import com.cloudesire.azure.client.apiobjects.enums.DeploymentSlot;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.tisana4j.RestClient;
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServiceClientImpl extends BaseClientImpl implements ServiceClient
{
    private ObjectCache<Images> imageCache;

    public ServiceClientImpl( URL endpoint, final AzureClient restClient ) throws MalformedURLException
    {
        super( endpoint, restClient );
        imageCache = new ObjectCache<>( new ObjectCache.CacheLoader<Images>()
        {
            @Override
            public Images loadObjects() throws AzureClientException, MalformedURLException, RestException
            {
                return restClient.getClient()
                        .get( new URL( ServiceClientImpl.this.endpoint, "images" ), Images.class );
            }

        }, 1, TimeUnit.SECONDS );
    }

    @Override
    protected String getEndpoint()
    {
        return "services/";
    }

    @Override
    public OSImage findImage( String name ) throws RestException, AzureClientException, MalformedURLException
    {
        List<OSImage> images = listImages();
        OSImage candidate = null;
        for ( OSImage i : images )
        {
            if ( !i.getName().equals( name ) ) continue;
            if ( candidate == null ) candidate = i;
        }
        return candidate;
    }

    @Override
    public List<OSImage> listImages() throws MalformedURLException, RestException, AzureClientException
    {
        return imageCache.get().getOsImages();
    }

    @Override
    public Deployment getDeployment( String serviceName, String deploymentName )
            throws MalformedURLException, RestException, AzureClientException
    {

        return restClient.getClient().get( new URL( this.endpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName ), Deployment.class );
    }

    @Override
    public Deployment getDeploymentBySlot( String serviceName, DeploymentSlot deploymentSlot )
            throws MalformedURLException, RestException, AzureClientException
    {

        return restClient.getClient().get( new URL( this.endpoint,
                        "hostedservices/" + serviceName + "/deploymentslots/" + deploymentSlot.toString() ),
                Deployment.class );
    }

    @Override
    public String createCloudService( CloudService cloudService )
            throws MalformedURLException, RestException, AzureClientException
    {
        RestClient restClient = this.restClient.getClient();

        restClient.post( new URL( ServiceClientImpl.this.endpoint, "hostedservices" ), cloudService, null, null );

        return getOperationId( restClient );
    }

    @Override
    public String createStorageService( StorageService storageService )
            throws MalformedURLException, RestException, AzureClientException
    {
        RestClient restClient = this.restClient.getClient();
        restClient.post( new URL( ServiceClientImpl.this.endpoint, "storageservices" ), storageService, null,
                null );

        return getOperationId( restClient );
    }

    @Override
    public void deleteStorageService( String storageServiceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .delete( new URL( ServiceClientImpl.this.endpoint, "storageservices/" + storageServiceName ) );
    }

    @Override
    public List<StorageService> listStorageServices() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint, "storageservices" ), StorageServices.class )
                .getStorageServices();
    }

    @Override
    public String addServiceCertificate( String data, String format, String password, String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        CertificateFile certificate = new CertificateFile();
        certificate.setData( data );
        certificate.setCertificateFormat( format );
        certificate.setPassword( password );

        RestClient restClient = this.restClient.getClient();
        restClient.post(
                new URL( ServiceClientImpl.this.endpoint, "hostedservices/" + serviceName + "/certificates" ),
                certificate, null, null );

        return getOperationId( restClient );
    }

    @Override
    public String createDeployment( Deployment deployment, String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        RestClient restClient = this.restClient.getClient();
        restClient.post(
                new URL( ServiceClientImpl.this.endpoint, "hostedservices/" + serviceName + "/deployments" ),
                deployment, null, null );

        return getOperationId( restClient );
    }

    @Override
    public String destroyCloudService( String serviceName, boolean cascadeDelete )
            throws MalformedURLException, RestException, AzureClientException
    {
        if ( cascadeDelete ) serviceName = serviceName.concat( "?comp=media" );
        URL url = new URL( ServiceClientImpl.this.endpoint, "hostedservices/" + serviceName );
        RestClient restClient = this.restClient.getClient();
        restClient.delete( url );

        return getOperationId( restClient );
    }

    @Override
    public String resumeMachine( String serviceName, String deploymentName, String roleName )
            throws MalformedURLException, RestException, AzureClientException
    {
        StartRoleOperation startRoleOperation = new StartRoleOperation();
        startRoleOperation.setOperationType( "StartRoleOperation" );

        RestClient restClient = this.restClient.getClient();
        restClient.post( new URL( ServiceClientImpl.this.endpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roleinstances/" + roleName
                        + "/Operations" ), startRoleOperation, null, null );

        return getOperationId( restClient );
    }

    @Override
    public String stopMachine( String serviceName, String deploymentName, String roleName )
            throws MalformedURLException, RestException, AzureClientException
    {
        ShutdownRoleOperation shutdownRoleOperation = new ShutdownRoleOperation();
        shutdownRoleOperation.setOperationType( "ShutdownRoleOperation" );

        RestClient restClient = this.restClient.getClient();
        restClient.post( new URL( ServiceClientImpl.this.endpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roleinstances/" + roleName
                        + "/Operations" ), shutdownRoleOperation, null, null );

        return getOperationId( restClient );
    }

    @Override
    public Boolean isCloudServiceAvailable( String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        AvailabilityResponse availabilityResponse = restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint,
                        "hostedservices/operations/isavailable/" + serviceName ), AvailabilityResponse.class );

        return Boolean.valueOf( availabilityResponse.getResult() );
    }

    @Override
    public String addDataDisk( String serviceName, String deploymentName, String roleName, DataVirtualHardDisk disk )
            throws MalformedURLException, RestException, AzureClientException
    {
        RestClient restClient = this.restClient.getClient();
        restClient.post( new URL( ServiceClientImpl.this.endpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roles/" + roleName
                        + "/DataDisks" ), disk, null, null );

        return getOperationId( restClient );
    }

    @Override
    public String createReservedIP( ReservedIP reservedIP )
            throws MalformedURLException, RestException, AzureClientException
    {
        RestClient restClient = this.restClient.getClient();
        restClient
                .post( new URL( ServiceClientImpl.this.endpoint, "networking/reservedips" ), reservedIP, null,
                        null );

        return getOperationId( restClient );
    }

    @Override
    public ReservedIP getReservedIP( String name ) throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint, "networking/reservedips/" + name ),
                        ReservedIP.class );
    }

    @Override
    public List<ReservedIP> getReservedIPs() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint, "networking/reservedips" ), ReservedIPs.class )
                .getReservedIPs();
    }

    @Override
    public void deleteReservedIP( String name ) throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .delete( new URL( ServiceClientImpl.this.endpoint, "networking/reservedips/" + name ) );
    }

    @Override
    public Boolean reservedIPExists( String name ) throws MalformedURLException, RestException, AzureClientException
    {
        try
        {
            this.getReservedIP( name );
            return true;
        }
        catch ( AzureResponseException e )
        {
            if ( e.getResponseCode() == 404 ) return false;
            throw e;
        }
    }

    @Override
    public List<ExtensionImage> listExtensionImages() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint, "extensions" ), ExtensionImages.class )
                .getExtensionImages();
    }

    @Override
    public List<ResourceExtension> listResourceExtensions()
            throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient().get( new URL( ServiceClientImpl.this.endpoint, "resourceextensions" ),
                ResourceExtensions.class ).getResourceExtensions();
    }

    @Override
    public List<HostedService> listCloudServices() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint, "hostedservices" ), HostedServices.class )
                .getHostedServices();
    }

    @Override
    public HostedService getCloudService( String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.endpoint, "hostedservices/" + serviceName ),
                        HostedService.class );
    }
}
