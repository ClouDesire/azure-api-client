package com.cloudesire.azure.client;

import com.cloudesire.tisana4j.RestClient;

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
	private final RestClient restClient;
	private URL endpoint;
	private ServiceClientImpl serviceClient;
	private ConfigurationClientImpl configurationClient;
	private OperationClientImpl operationClient;

	public AzureClient( String subscriptionId, KeyStore keyStore, String password, URL endpoint ) throws Exception
	{
		if ( subscriptionId == null ) throw new IllegalArgumentException("A subscription id is required");
		if ( keyStore == null ) throw new IllegalArgumentException("KeyStore is required");
		if ( password == null ) throw new IllegalArgumentException("KeyStore Password is required");
		if ( endpoint == null ) endpoint = new URL("https://management.core.windows.net");

		this.endpoint = new URL(endpoint, subscriptionId + "/");
		Map<String, String> defaultHeaders = new HashMap<>();
		defaultHeaders.put("x-ms-version", "2013-08-01");
		restClient = new RestClient(null, null, true, defaultHeaders, generateSSLSocketFactory(keyStore, password));
		restClient.setExceptionTranslator(new AzureExceptionTranslator());
		restClient.setUseXml(Boolean.TRUE);
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
		tmf.init(keyStore);

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
		return sslContext;
	}

	public synchronized ConfigurationClient getConfigurationClient()
	{
		if ( configurationClient == null ) configurationClient = new ConfigurationClientImpl(
				endpoint, restClient
		);
		return configurationClient;

	}

	public synchronized ServiceClient getServiceClient() throws MalformedURLException
	{
		if ( serviceClient == null ) serviceClient = new ServiceClientImpl(
				endpoint, restClient
		);
		return serviceClient;
	}

	public synchronized OperationClient getOperationClient() throws MalformedURLException
	{
		if ( operationClient == null ) operationClient = new OperationClientImpl(
				endpoint, restClient
		);
		return operationClient;
	}
}
