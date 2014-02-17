package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AvailabilityResponse;
import com.cloudesire.azure.client.apiobjects.CertificateFile;
import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.DataVirtualHardDisk;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.ShutdownRoleOperation;
import com.cloudesire.azure.client.apiobjects.StartRoleOperation;
import com.cloudesire.azure.client.apiobjects.StorageService;
import com.cloudesire.azure.client.apiobjects.StorageServices;
import com.cloudesire.tisana4j.RestClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public class ServiceClientImpl implements ServiceClient
{
	private final URL servicesEndpoint;
	private final RestClient restClient;
	private ObjectCache<Images> imageCache;
	private final String XMSID = "x-ms-request-id";

	public ServiceClientImpl( URL endpoint, RestClient restClient ) throws MalformedURLException
	{
		this.restClient = restClient;
		this.servicesEndpoint = new URL(endpoint, "services/");

		imageCache = new ObjectCache<>(
				new ObjectCache.CacheLoader<Images>()
				{
					@Override
					public Images loadObjects() throws Exception
					{
						return ServiceClientImpl.this.restClient.get(
								new URL(
										ServiceClientImpl.this.servicesEndpoint, "images"
								), Images.class
						);
					}

				}, 1, TimeUnit.SECONDS
		);
	}

	@Override
	public OSImage findImage( String name ) throws Exception
	{
		List<OSImage> images = listImages();
		OSImage candidate = null;
		for ( OSImage i : images )
		{
			if ( !i.getName().equals(name) ) continue;
			if ( candidate == null ) candidate = i;
		}
		return candidate;
	}

	@Override
	public List<OSImage> listImages() throws Exception
	{
		return imageCache.get().getOsImages();
	}

	public Deployment getDeployment( String serviceName, String deploymentName ) throws Exception
	{
		Deployment deployment = ServiceClientImpl.this.restClient.get(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments/" + deploymentName
				), Deployment.class
		);

		return deployment;
	}

	@Override
	public String createCloudService( CloudService cloudService ) throws Exception
	{
		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices"
				), cloudService, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public String createStorageService( StorageService storageService ) throws Exception
	{
		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "storageservices"
				), storageService, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public void deleteStorageService( String storageServiceName ) throws Exception
	{
		ServiceClientImpl.this.restClient.delete(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "storageservices/" + storageServiceName
				)
		);
	}

	@Override
	public List<StorageService> listStorageServices() throws Exception
	{
		return ServiceClientImpl.this.restClient.get(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "storageservices"
				), StorageServices.class
		).getStorageServices();
	}

	@Override
	public String addServiceCertificate(
			String data, String format, String password, String serviceName ) throws Exception
	{
		CertificateFile certificate = new CertificateFile();
		certificate.setData(data);
		certificate.setCertificateFormat(format);
		certificate.setPassword(password);

		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/certificates"
				), certificate, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public String createDeployment( Deployment deployment, String serviceName ) throws Exception
	{
		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments"
				), deployment, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public String destroyCloudService( String serviceName, boolean cascadeDelete ) throws Exception
	{
		if ( cascadeDelete )
			serviceName = serviceName.concat("?comp=media");
		URL url = new URL(ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName);
		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.delete(url, null, responseHeaders);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public String resumeMachine( String serviceName, String deploymentName, String roleName ) throws Exception
	{
		StartRoleOperation startRoleOperation = new StartRoleOperation();
		startRoleOperation.setOperationType("StartRoleOperation");

		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roleinstances/" + roleName + "/Operations"
				), startRoleOperation, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public String stopMachine( String serviceName, String deploymentName, String roleName ) throws Exception
	{
		ShutdownRoleOperation shutdownRoleOperation = new ShutdownRoleOperation();
		shutdownRoleOperation.setOperationType("ShutdownRoleOperation");

		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roleinstances/" + roleName + "/Operations"
				), shutdownRoleOperation, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}

	@Override
	public Boolean isCloudServiceAvailable( String serviceName ) throws Exception
	{
		AvailabilityResponse availabilityResponse = ServiceClientImpl.this.restClient.get(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/operations/isavailable/" + serviceName
				), AvailabilityResponse.class
		);

		return Boolean.valueOf(availabilityResponse.getResult());
	}

	@Override
	public String addDataDisk(
			String serviceName, String deploymentName, String roleName, DataVirtualHardDisk disk ) throws Exception
	{
		Map<String, String> responseHeaders = new HashMap<>();
		ServiceClientImpl.this.restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments/" + deploymentName + "/roles/" + roleName + "/DataDisks"
				), disk, null, null, responseHeaders
		);

		if ( !responseHeaders.containsKey(XMSID) ) return null;
		return responseHeaders.get(XMSID);
	}
}
