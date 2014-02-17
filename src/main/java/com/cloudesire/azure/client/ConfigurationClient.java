package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AffinityGroup;
import com.cloudesire.azure.client.apiobjects.Location;

import java.util.List;

public interface ConfigurationClient
{
	List<Location> listLocations () throws Exception;

	String createAffinityGroup ( AffinityGroup group ) throws Exception;

    void deleteAffinityGroup ( String affinityGroupName ) throws Exception;

    List<AffinityGroup> listAffinityGroups () throws Exception;
}
