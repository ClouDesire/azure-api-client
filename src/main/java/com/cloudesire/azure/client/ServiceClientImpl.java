package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.StorageService;
import com.cloudesire.tisana4j.RestClient;
import org.apache.commons.codec.binary.Base64;

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

	public ServiceClientImpl ( URL endpoint, RestClient restClient ) throws MalformedURLException
	{
		this.restClient = restClient;
		this.servicesEndpoint = new URL(endpoint, "services/");

		imageCache = new ObjectCache<>(
				new ObjectCache.CacheLoader<Images>()
				{
					@Override
					public Images loadObjects () throws Exception
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
	public OSImage findImage ( String name ) throws Exception
	{
		List<OSImage> images = listImages();
		OSImage candidate = null;
		for (OSImage i : images)
		{
			if (! i.getName().equals(name)) continue;
			if (candidate == null) candidate = i;
		}
		return candidate;
	}

	@Override
	public List<OSImage> listImages () throws Exception
	{
		return imageCache.get().getOsImages();
	}

	@Override
	public String createCloudService ( CloudService cloudService ) throws Exception
	{
		if (cloudService.getLabel() != null && ! Base64.isBase64(cloudService.getLabel()))
			throw new AzureResponseException("400", "Label must be 64 base encoded");

		// TODO: check avaibility of ServiceName
		Map<String, String> responseHeaders = new HashMap<>();
		restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices"
				), cloudService, null, null, responseHeaders
		);

		if (! responseHeaders.containsKey("x-ms-request-id")) return null;
		return responseHeaders.get("x-ms-request-id");
	}

	@Override
	public String createStorageService ( StorageService storageService ) throws Exception
	{
		if (storageService.getLabel() != null && ! Base64.isBase64(storageService.getLabel()))
			throw new AzureResponseException("400", "Label must be 64 base encoded");

		Map<String, String> responseHeaders = new HashMap<>();
		restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "storageservices"
				), storageService, null, null, responseHeaders
		);

		if (! responseHeaders.containsKey("x-ms-request-id")) return null;
		return responseHeaders.get("x-ms-request-id");
	}

	@Override
	public String createDeployment ( Deployment deployment, String serviceName ) throws Exception
	{
		Map<String, String> responseHeaders = new HashMap<>();
		restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments"
				), deployment, null, null, responseHeaders
		);

		if (! responseHeaders.containsKey("x-ms-request-id")) return null;
		return responseHeaders.get("x-ms-request-id");
	}
}
