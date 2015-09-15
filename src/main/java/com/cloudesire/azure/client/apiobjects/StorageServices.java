package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "StorageServices")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class StorageServices
{
	@XmlElement (name = "StorageService")
	private List<StorageService> storageServices;

	public List<StorageService> getStorageServices()
	{
		return storageServices;
	}

	public void setStorageServices( List<StorageService> storageServices )
	{
		this.storageServices = storageServices;
	}
}
