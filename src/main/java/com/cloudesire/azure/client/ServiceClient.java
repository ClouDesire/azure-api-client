package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.OSImage;

import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public interface ServiceClient
{
	OSImage findImage ( String name ) throws Exception;

	List<OSImage> listImages () throws Exception;

	CloudService createCloudService ( CloudService  cloudService  ) throws Exception;
}
