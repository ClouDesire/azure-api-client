package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.tisana4j.RestClient;
import org.apache.commons.codec.binary.Base64;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
	public CloudService createCloudService ( CloudService cloudService ) throws Exception
	{
		if (cloudService.getLabel() != null && ! Base64.isBase64(cloudService.getLabel()))
			throw new AzureResponseException("400", "Label must be 64 base encoded");

		// Azure returns none on post requests ._.
		// FIXME: check avaibility of ServiceName
		restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices"
				), cloudService, null, null
		);

		// FIXME: list CloudServices from Azure and return the right one
		return cloudService;
	}

	@Override
	public Deployment createDeployment ( Deployment deployment, String serviceName ) throws Exception
	{
		restClient.post(
				new URL(
						ServiceClientImpl.this.servicesEndpoint, "hostedservices/" + serviceName + "/deployments"
				), deployment, null, null
		);

		return deployment;
	}

}
