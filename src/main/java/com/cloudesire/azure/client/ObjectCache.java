package com.cloudesire.azure.client;

import com.cloudesire.azure.client.exceptions.AzureClientException;
import com.cloudesire.tisana4j.exceptions.RestException;
import com.google.common.collect.ImmutableList;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObjectCache<T>
{
    interface CacheLoader<T>
    {
        T loadObjects() throws AzureClientException, MalformedURLException, RestException;
    }

    private final CacheLoader<T> loader;
    private final long duration;
    private final TimeUnit unit;

    private volatile long lastLoad = 0;

    private volatile T objects;

    public ObjectCache( CacheLoader<T> loader, long duration, TimeUnit unit )
    {
        this.loader = loader;
        this.duration = duration;
        this.unit = unit;
    }

    public synchronized T get() throws AzureClientException, MalformedURLException, RestException
    {
        if ( System.currentTimeMillis() > lastLoad + unit.toMillis( duration ) )
        {
            objects = loader.loadObjects();
            lastLoad = System.currentTimeMillis();
        }
        List<T> listObjects = ImmutableList.of( objects );

        return ImmutableList.copyOf( listObjects ).get( 0 );
    }

}
