package com.cloudesire.azure.client;

import com.cloudesire.azure.client.ObjectCache.CacheLoader;
import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.tisana4j.RestClient;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ConfigurationClientImpl implements ConfigurationClient
{

	private final URL endpoint;
	private final RestClient restClient;
	private ObjectCache<Images> imageCache;

	public ConfigurationClientImpl(URL endpoint, RestClient restClient)
	{
		this.endpoint = endpoint;
		this.restClient = restClient;

		imageCache = new ObjectCache<>(new CacheLoader<Images>()
		{
			@Override
			public Images loadObjects () throws Exception
			{
				return ConfigurationClientImpl.this.restClient.get(
						new URL(
								ConfigurationClientImpl.this.endpoint, "services/images"
						), Images.class
				);
			}

		}, 1, TimeUnit.DAYS);
	}

	@Override
	public OSImage findImage ( String name ) throws Exception
	{
		List<OSImage> images = listImages();
		OSImage candidate = null;
		for (OSImage i : images)
		{
			if (!i.getName().equals(name)) continue;
			if (candidate == null) candidate = i;
		}
		return candidate;
	}

	@Override
	public List<OSImage> listImages () throws Exception
	{
		return imageCache.get().getOsImages();
	}

}
