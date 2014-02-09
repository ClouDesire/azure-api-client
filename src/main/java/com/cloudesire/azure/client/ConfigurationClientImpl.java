package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AffinityGroup;
import com.cloudesire.azure.client.apiobjects.Location;
import com.cloudesire.azure.client.apiobjects.Locations;
import com.cloudesire.tisana4j.RestClient;
import org.apache.commons.codec.binary.Base64;

import java.net.URL;
import java.util.List;

class ConfigurationClientImpl implements ConfigurationClient
{

	private final URL endpoint;
	private final RestClient restClient;

	public ConfigurationClientImpl ( URL endpoint, RestClient restClient )
	{
		this.endpoint = endpoint;
		this.restClient = restClient;
	}

	@Override
	public List<Location> listLocations () throws Exception
	{
		return ConfigurationClientImpl.this.restClient.get(
				new URL(
						ConfigurationClientImpl.this.endpoint, "locations"
				), Locations.class
		).getLocations();
	}

	@Override
	public AffinityGroup createAffinityGroup ( AffinityGroup group ) throws Exception
	{
		if (group.getLabel() != null && ! Base64.isBase64(group.getLabel()))
			throw new AzureResponseException("400", "Label must be 64 base encoded");

		restClient.post(
				new URL(
						ConfigurationClientImpl.this.endpoint, "affinitygroups"
				), group, null, null
		);

		return group;
	}

}
