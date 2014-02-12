package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.AzureClient;
import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Location;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

		/*AffinityGroup affinityGroup = new AffinityGroup();
		affinityGroup.setName(UUID.randomUUID().toString());
		affinityGroup.setLabel(Base64.encode(affinityGroup.getName().getBytes("UTF-8")));
		affinityGroup.setLocation(locations.iterator().next().getName());
		final String groupID = client.getConfigurationClient().createAffinityGroup(affinityGroup);
		log.info("Requested AffinityGroup: " + affinityGroup + " ID: " + groupID);*/

		// Create CloudService
		/*CloudService c = new CloudService();
		c.setServiceName(UUID.randomUUID().toString());
		c.setLabel(Base64.encode(c.getServiceName().getBytes("UTF-8")));
		c.setDescription(c.getServiceName());
		c.setAffinityGroup(affinityGroup.getName());
		final String cloudServiceID = client.getServiceClient().createCloudService(c);
		log.info("Requested CloudService: " + c + " ID: " + cloudServiceID);*/

		/*StorageService s = new StorageService();
		s.setServiceName("myst0rages3rvic399");
		s.setLabel(Base64.encode(s.getServiceName().getBytes("UTF-8")));
		s.setAffinityGroup(affinityGroup.getName());
		final String storageServiceID = client.getServiceClient().createStorageService(s);
		log.info("Requested StorageService: " + s + " ID: " + storageServiceID);*/

		BASE64Encoder encoder = new BASE64Encoder();

		CloudService c = new CloudService();
		c.setServiceName("cloudesiretest");
		c.setLabel(encoder.encode(c.getServiceName().getBytes()));
		c.setDescription(c.getServiceName());
		c.setAffinityGroup("eu-cloudesire");
		final String cloudServiceID = client.getServiceClient().createCloudService(c);
		log.debug("Created CloudService: " + c + " ID: " + cloudServiceID);

		// Test Deploy Virtual Machine
		String name = "test-120";
		Deployment.Builder deploymentBuilder = new Deployment.Builder();
		Deployment deployment = deploymentBuilder
				.withName(name)
				.withHostname("hostname")
				.withLabel(UUID.randomUUID().toString())
				.withMinCpu(1)
				.withMinDisk(50)
				.withMinMemory(100)
				.withPassword("askd123ASDASD1213")
				.withSourceImage(testOsImage.getName())
				.withSourceImageLink("http://" + "cloudesiretest" + ".blob.core.windows.net/communityimages/" + name + UUID.randomUUID().toString() + ".vhd")
				.withDataImageLink("http://" + "cloudesiretest" + ".blob.core.windows.net/disks/" + name + UUID.randomUUID().toString() + ".vhd")
				.withUsername("manuel")
				.build();

		deployment
				.getRoleList().iterator().next()
				.getConfigurationSets()
				.getConfigurationSets().iterator().next()
				.setDisableSshPasswordAuthentication(false);

		final String deploymentID = client.getServiceClient().createDeployment(deployment, "cloudesiretest");
		log.info("Waiting creation deployment ID: " + deploymentID.toString());

		client.getOperationClient().waitForState(deploymentID, Status.SUCCEEDED, 5, TimeUnit.MINUTES);

		Deployment ret = client.getServiceClient().getDeployment("cloudesiretest", deployment.getName());
		log.info("Deployment created: " + ret + " ID: " + deploymentID);

		log.info("Shutdow now test!!");
		String shutupID = client.getServiceClient().stopMachine("cloudesiretest", deployment.getName(), ret.getRoleInstanceList().getRoles().iterator().next().getRoleName());
		log.info("Waiting...");
		client.getOperationClient().waitForState(shutupID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("TANGO DOWN !!");

		log.info("Start now test!!");
		String startID = client.getServiceClient().resumeMachine("cloudesiretest", deployment.getName(), ret.getRoleInstanceList().getRoles().iterator().next().getRoleName());
		log.info("Waiting...");
		client.getOperationClient().waitForState(startID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("READY !!");

		log.info("Killing machine..");
		String killID = client.getServiceClient().destroyCloudService(c.getServiceName(), Boolean.TRUE);
		log.info("Waiting...");
		client.getOperationClient().waitForState(killID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("DESTROYED UAHAU !!");
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
