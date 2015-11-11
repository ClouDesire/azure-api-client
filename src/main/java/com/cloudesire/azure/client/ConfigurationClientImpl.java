package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.AffinityGroup;
import com.cloudesire.azure.client.apiobjects.AffinityGroups;
import com.cloudesire.azure.client.apiobjects.Location;
import com.cloudesire.azure.client.apiobjects.Locations;
import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.tisana4j.exceptions.RestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

class ConfigurationClientImpl extends BaseClientImpl implements ConfigurationClient
{
    public ConfigurationClientImpl( URL endpoint, AzureClient restClient ) throws MalformedURLException
    {
        super( endpoint, restClient );
    }

    @Override
    protected String getEndpoint()
    {
        return null;
    }

    @Override
    public List<Location> listLocations() throws MalformedURLException, AzureClientException, RestException
    {
        return restClient.getClient()
                .get( new URL( ConfigurationClientImpl.this.endpoint, "locations" ), Locations.class ).getLocations();
    }

    @Override
    public String createAffinityGroup( AffinityGroup group )
            throws MalformedURLException, AzureClientException, RestException
    {
        restClient.getClient()
                .post( new URL( ConfigurationClientImpl.this.endpoint, "affinitygroups" ), group, null, null );

        Map<String, List<String>> responseHeaders = restClient.getClient().getLastResponseHeaders();

        if ( !responseHeaders.containsKey( "x-ms-request-id" ) ) return null;
        return responseHeaders.get( "x-ms-request-id" ).get( 0 );
    }

    @Override
    public void deleteAffinityGroup( String affinityGroupName )
            throws MalformedURLException, AzureClientException, RestException
    {
        restClient.getClient()
                .delete( new URL( ConfigurationClientImpl.this.endpoint, "affinitygroups/" + affinityGroupName ) );
    }

    @Override
    public List<AffinityGroup> listAffinityGroups() throws MalformedURLException, AzureClientException, RestException
    {
        return restClient.getClient()
                .get( new URL( ConfigurationClientImpl.this.endpoint, "affinitygroups" ), AffinityGroups.class )
                .getAffinityGroups();
    }
}
