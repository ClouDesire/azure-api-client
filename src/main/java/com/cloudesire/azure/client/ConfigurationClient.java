package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.OSImage;

import java.util.List;

public interface ConfigurationClient
{
	OSImage findImage ( String name ) throws Exception;

	List<OSImage> listImages () throws Exception;
}
