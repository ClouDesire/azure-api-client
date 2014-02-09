package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.AzureClient;
import com.cloudesire.azure.client.apiobjects.AffinityGroup;
import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Location;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.StorageService;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.List;
import java.util.UUID;

public class ClientTest
{
	private static Logger log = LoggerFactory.getLogger(ClientTest.class);

	private static String cert = "/home/manuelmazzuola/tmp/WindowsAzureKeyStore.jks";
	private static String password = "cloudesire";
	private static String id = "3517e125-019c-427d-bc47-f795fc04d6aa";

	public static void main ( String[] args ) throws Exception
	{
		KeyStore keyStore = getKeyStore(cert, password);
		final AzureClient client = new AzureClient(id, keyStore, password, null);

		//Test listImages
		/*final List<OSImage> images = client.getServiceClient().listImages();
		log.debug("Images: ");
		for(OSImage o : images)
		{
			log.debug(" OSImage: " + o);
		}*/

		// Test findImage
		final OSImage testOsImage = client.getServiceClient().findImage("b39f27a8b8c64d52b05eac6a62ebad85__Ubuntu-12_04_3-LTS-amd64-server-20140130-en-us-30GB");
		log.info("Test candidate: " + testOsImage);

		// Test listLocation
		final List<Location> locations = client.getConfigurationClient().listLocations();
		for (Location location : locations)
		{
			System.out.println(location);
		}

		AffinityGroup affinityGroup = new AffinityGroup();
		affinityGroup.setName(UUID.randomUUID().toString());
		affinityGroup.setLabel(Base64.encode(affinityGroup.getName().getBytes("UTF-8")));
		affinityGroup.setLocation(locations.iterator().next().getName());
		final AffinityGroup group = client.getConfigurationClient().createAffinityGroup(affinityGroup);
		log.info("Requested AffinityGroup: " + affinityGroup);

		// Create CloudService
		CloudService c = new CloudService();
		c.setServiceName(UUID.randomUUID().toString());
		c.setLabel(Base64.encode(c.getServiceName().getBytes("UTF-8")));
		c.setDescription(c.getServiceName());
		c.setAffinityGroup(affinityGroup.getName());
		final CloudService cloudService = client.getServiceClient().createCloudService(c);
		log.info("Requested CloudService: " + cloudService);

		StorageService s = new StorageService();
		s.setServiceName("myst0rages3rvic399");
		s.setLabel(Base64.encode(s.getServiceName().getBytes("UTF-8")));
		s.setAffinityGroup(group.getName());
		final StorageService storageService = client.getServiceClient().createStorageService(s);
		log.info("Requested StorageService: " + storageService);

		// Test Deploy Virtual Machine
		Deployment.Builder deploymentBuilder = new Deployment.Builder();
		Deployment deployment = deploymentBuilder
				.withName(UUID.randomUUID().toString())
				.withHostname("hostname")
				.withLabel(UUID.randomUUID().toString())
				.withMinCpu(1)
				.withMinDisk(100)
				.withMinMemory(100)
				.withPassword("askd123ASDASD1213")
				.withSourceImage(testOsImage.getName())
				.withSourceImageLink("http://" + storageService.getServiceName() + ".blob.core.windows.net/communityimages/" + UUID.randomUUID().toString() + ".vhd")
				.withUsername("manuel")
				.build();

		deployment
				.getRoleList().iterator().next()
				.getConfigurationSets()
				.getConfigurationSets().iterator().next()
				.setDisableSshPasswordAuthentication(false);

		final Deployment deployment1 = client.getServiceClient().createDeployment(deployment, cloudService.getServiceName());
		log.info("Deployment : " + deployment1);
	}

	// http://gauravmantri.com/2013/08/25/consuming-windows-azure-service-management-api-in-java/
	// copy and paste
	private static KeyStore getKeyStore ( String keyStoreName, String password ) throws IOException
	{
		KeyStore ks = null;
		FileInputStream fis = null;
		try
		{
			ks = KeyStore.getInstance("JKS");
			char[] passwordArray = password.toCharArray();
			fis = new java.io.FileInputStream(keyStoreName);
			ks.load(fis, passwordArray);
			fis.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (fis != null)
			{
				fis.close();
			}
		}
		return ks;
	}
}
