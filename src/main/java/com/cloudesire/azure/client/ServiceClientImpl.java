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
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ServiceClientImpl implements ServiceClient
{
    private final URL servicesEndpoint;
    private final AzureClient restClient;
    private final String XMSID = "x-ms-request-id";
    private ObjectCache<Images> imageCache;

    public ServiceClientImpl( URL endpoint, final AzureClient restClient ) throws MalformedURLException
    {
        this.restClient = restClient;
        this.servicesEndpoint = new URL( endpoint, "services/" );

        imageCache = new ObjectCache<>( new ObjectCache.CacheLoader<Images>()
        {
            @Override
            public Images loadObjects() throws AzureClientException, MalformedURLException, RestException
            {
                return restClient.getClient()
                        .get( new URL( ServiceClientImpl.this.servicesEndpoint, "images" ), Images.class );
            }

        }, 1, TimeUnit.SECONDS );
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
        Deployment deployment = restClient.getClient().get( new URL( ServiceClientImpl.this.servicesEndpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName ), Deployment.class );

        return deployment;
    }

    @Override
    public Deployment getDeploymentBySlot( String serviceName, DeploymentSlot deploymentSlot )
            throws MalformedURLException, RestException, AzureClientException
    {
        Deployment deployment = restClient.getClient().get( new URL( ServiceClientImpl.this.servicesEndpoint,
                        "hostedservices/" + serviceName + "/deploymentslots/" + deploymentSlot.toString() ),
                Deployment.class );

        return deployment;
    }

    @Override
    public String createCloudService( CloudService cloudService )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .post( new URL( ServiceClientImpl.this.servicesEndpoint, "hostedservices" ), cloudService, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public String createStorageService( StorageService storageService )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .post( new URL( ServiceClientImpl.this.servicesEndpoint, "storageservices" ), storageService, null,
                        null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public void deleteStorageService( String storageServiceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .delete( new URL( ServiceClientImpl.this.servicesEndpoint, "storageservices/" + storageServiceName ) );
    }

    @Override
    public List<StorageService> listStorageServices() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.servicesEndpoint, "storageservices" ), StorageServices.class )
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

        restClient.getClient().post( new URL( ServiceClientImpl.this.servicesEndpoint,
                "hostedservices/" + serviceName + "/certificates" ), certificate, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public String createDeployment( Deployment deployment, String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient().post( new URL( ServiceClientImpl.this.servicesEndpoint,
                "hostedservices/" + serviceName + "/deployments" ), deployment, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public String destroyCloudService( String serviceName, boolean cascadeDelete )
            throws MalformedURLException, RestException, AzureClientException
    {
        if ( cascadeDelete ) serviceName = serviceName.concat( "?comp=media" );
        URL url = new URL( ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName );
        restClient.getClient().delete( url );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public String resumeMachine( String serviceName, String deploymentName, String roleName )
            throws MalformedURLException, RestException, AzureClientException
    {
        StartRoleOperation startRoleOperation = new StartRoleOperation();
        startRoleOperation.setOperationType( "StartRoleOperation" );

        restClient.getClient().post( new URL( ServiceClientImpl.this.servicesEndpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roleinstances/" + roleName
                        + "/Operations" ), startRoleOperation, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public String stopMachine( String serviceName, String deploymentName, String roleName )
            throws MalformedURLException, RestException, AzureClientException
    {
        ShutdownRoleOperation shutdownRoleOperation = new ShutdownRoleOperation();
        shutdownRoleOperation.setOperationType( "ShutdownRoleOperation" );

        restClient.getClient().post( new URL( ServiceClientImpl.this.servicesEndpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roleinstances/" + roleName
                        + "/Operations" ), shutdownRoleOperation, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public Boolean isCloudServiceAvailable( String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        AvailabilityResponse availabilityResponse = restClient.getClient()
                .get( new URL( ServiceClientImpl.this.servicesEndpoint,
                        "hostedservices/operations/isavailable/" + serviceName ), AvailabilityResponse.class );

        return Boolean.valueOf( availabilityResponse.getResult() );
    }

    @Override
    public String addDataDisk( String serviceName, String deploymentName, String roleName, DataVirtualHardDisk disk )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient().post( new URL( ServiceClientImpl.this.servicesEndpoint,
                "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roles/" + roleName
                        + "/DataDisks" ), disk, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public String createReservedIP( ReservedIP reservedIP )
            throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .post( new URL( ServiceClientImpl.this.servicesEndpoint, "networking/reservedips" ), reservedIP, null,
                        null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( XMSID ) ) return null;
        return responseHeaders.get( XMSID ).get( 0 );
    }

    @Override
    public ReservedIP getReservedIP( String name ) throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.servicesEndpoint, "networking/reservedips/" + name ),
                        ReservedIP.class );
    }

    @Override
    public List<ReservedIP> getReservedIPs() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.servicesEndpoint, "networking/reservedips" ), ReservedIPs.class )
                .getReservedIPs();
    }

    @Override
    public void deleteReservedIP( String name ) throws MalformedURLException, RestException, AzureClientException
    {
        restClient.getClient()
                .delete( new URL( ServiceClientImpl.this.servicesEndpoint, "networking/reservedips/" + name ) );
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
                .get( new URL( ServiceClientImpl.this.servicesEndpoint, "extensions" ), ExtensionImages.class )
                .getExtensionImages();
    }

    @Override
    public List<ResourceExtension> listResourceExtensions()
            throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient().get( new URL( ServiceClientImpl.this.servicesEndpoint, "resourceextensions" ),
                ResourceExtensions.class ).getResourceExtensions();
    }

    @Override
    public List<HostedService> listCloudServices() throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.servicesEndpoint, "hostedservices" ), HostedServices.class )
                .getHostedServices();
    }

    @Override
    public HostedService getCloudService( String serviceName )
            throws MalformedURLException, RestException, AzureClientException
    {
        return restClient.getClient()
                .get( new URL( ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName ),
                        HostedService.class );
    }
}
