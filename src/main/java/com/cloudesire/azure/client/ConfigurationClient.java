package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AffinityGroup;
import com.cloudesire.azure.client.apiobjects.Location;

import java.util.List;

public interface ConfigurationClient
{
	List<Location> listLocations () throws Exception;

	Integer createAffinityGroup ( AffinityGroup group ) throws Exception;
}
