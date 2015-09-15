package com.cloudesire.azure.client;

import com.cloudesire.tisana4j.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger log = LoggerFactory.getLogger( AzureClient.class );

    private final RestClient restClient;
	private final boolean isEnabled;
    private URL endpoint;
	private ServiceClientImpl serviceClient;
	private ConfigurationClientImpl configurationClient;
	private OperationClientImpl operationClient;

	public AzureClient( String subscriptionId, KeyStore keyStore, String password, URL endpoint ) throws Exception
	{
        if ( subscriptionId == null ||  keyStore == null || password == null )
        {
		    this.isEnabled = false;
            this.restClient = null;
            log.warn( "Subscription id, KeyStore and Keystore Password are required. The client will be DISABLED." );
        }
        else
        {
            this.isEnabled = true;
            Map<String, String> defaultHeaders = new HashMap<>();
            defaultHeaders.put("x-ms-version", "2014-10-01");
            restClient = new RestClient(null, null, false, defaultHeaders, generateSSLSocketFactory(keyStore, password));
            restClient.setExceptionTranslator(new AzureExceptionTranslator());
            restClient.setUseXml(Boolean.TRUE);

        }
        if ( endpoint == null ) endpoint = new URL("https://management.core.windows.net");
        this.endpoint = new URL( endpoint, subscriptionId + "/" );
	}

	private SSLContext generateSSLSocketFactory( KeyStore keyStore, String password )
	throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, UnrecoverableKeyException
	{
		// http://stackoverflow.com/questions/859111/how-do-i-accept-a-self-signed-certificate-with-a-java-httpsurlconnection
		// and
		// http://gauravmantri.com/2013/08/25/consuming-windows-azure-service-management-api-in-java/
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		keyManagerFactory.init(keyStore, password.toCharArray());

		TrustManagerFactory tmf =
				TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init( keyStore );

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init( keyManagerFactory.getKeyManagers(), null, new SecureRandom() );
		return sslContext;
	}

	private void checkClientStatus ()
    {
        if ( !isEnabled ) throw new IllegalStateException ( "Missing credentials, client not enabled." );
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public synchronized ConfigurationClient getConfigurationClient()
	{
		checkClientStatus();
        if ( configurationClient == null ) configurationClient = new ConfigurationClientImpl(
				endpoint, restClient
		);
		return configurationClient;

	}

	public synchronized ServiceClient getServiceClient() throws MalformedURLException
	{
        checkClientStatus();
        if ( serviceClient == null ) serviceClient = new ServiceClientImpl(
				endpoint, restClient
		);
		return serviceClient;
	}

	public synchronized OperationClient getOperationClient() throws MalformedURLException
	{
        checkClientStatus();
        if ( operationClient == null ) operationClient = new OperationClientImpl(
				endpoint, restClient
		);
		return operationClient;
	}
}
