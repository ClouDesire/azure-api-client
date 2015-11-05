package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AffinityGroup;
import com.cloudesire.azure.client.apiobjects.Location;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.util.List;

public interface ConfigurationClient
{
    List<Location> listLocations() throws MalformedURLException, AzureClientException, RestException;

    String createAffinityGroup( AffinityGroup group ) throws MalformedURLException, AzureClientException, RestException;

    void deleteAffinityGroup( String affinityGroupName )
            throws MalformedURLException, AzureClientException, RestException;

    List<AffinityGroup> listAffinityGroups() throws MalformedURLException, AzureClientException, RestException;
}
