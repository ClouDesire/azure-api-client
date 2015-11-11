package com.cloudesire.azure.client;

import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.tisana4j.RestClient;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;

public class AzureClient
{
    private URL endpoint;
    private KeyStore keyStore;
    private String password;
    private String apiVersion;

    public AzureClient( String subscriptionId, KeyStore keyStore, String password ) throws AzureClientException
    {
        this( subscriptionId, keyStore, password, "https://management.core.windows.net" );
    }

    public AzureClient( String subscriptionId, KeyStore keyStore, String password, String endpoint )
            throws AzureClientException
    {
        this( subscriptionId, keyStore, password, endpoint, "2014-10-01" );
    }

    public AzureClient( String subscriptionId, KeyStore keyStore, String password, String endpoint, String apiVersion )
            throws AzureClientException
    {
        if ( StringUtils.isBlank( subscriptionId ) || keyStore == null || StringUtils.isBlank( password )
                || endpoint == null )
        {
            throw new AzureClientException( "You must supply all the required parameters" );
        }

        this.keyStore = keyStore;
        this.password = password;
        try
        {
            this.endpoint = new URL( new URL( endpoint ), subscriptionId + "/" );
        }
        catch ( MalformedURLException e )
        {
            throw new AzureClientException( e );
        }
        this.apiVersion = apiVersion;
    }

    public RestClient getClient() throws AzureClientException
    {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put( "x-ms-version", apiVersion );
        RestClient restClient = new RestClient( null, null, false, defaultHeaders, getCtx() );
        restClient.setExceptionTranslator( new AzureExceptionTranslator() );
        restClient.setUseXml( Boolean.TRUE );
        return restClient;
    }

    private SSLContext getCtx() throws AzureClientException
    {
        try
        {
            return generateSSLSocketFactory( keyStore, password );
        }
        catch ( NoSuchAlgorithmException | KeyStoreException | KeyManagementException | UnrecoverableKeyException e )
        {
            throw new AzureClientException( e );
        }
    }

    private SSLContext generateSSLSocketFactory( KeyStore keyStore, String password )
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, UnrecoverableKeyException
    {
        // http://stackoverflow.com/questions/859111/how-do-i-accept-a-self-signed-certificate-with-a-java-httpsurlconnection
        // and
        // http://gauravmantri.com/2013/08/25/consuming-windows-azure-service-management-api-in-java/
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance( "SunX509" );
        keyManagerFactory.init( keyStore, password.toCharArray() );

        TrustManagerFactory tmf = TrustManagerFactory.getInstance( TrustManagerFactory.getDefaultAlgorithm() );
        tmf.init( keyStore );

        SSLContext sslContext = SSLContext.getInstance( "TLS" );
        sslContext.init( keyManagerFactory.getKeyManagers(), null, new SecureRandom() );
        return sslContext;
    }

    public ConfigurationClient getConfigurationClient() throws AzureClientException, MalformedURLException
    {
        return new ConfigurationClientImpl( endpoint, this );
    }

    public ServiceClient getServiceClient() throws AzureClientException, MalformedURLException
    {
        return new ServiceClientImpl( endpoint, this );
    }

    public OperationClient getOperationClient() throws AzureClientException, MalformedURLException
    {
        return new OperationClientImpl( endpoint, this );
    }

}
