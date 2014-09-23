package com.cloudesire.azure.client.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyStore;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudesire.azure.client.AzureClient;
import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.DataVirtualHardDisk;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Deployment.Builder.OSFamily;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.enums.Status;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class ClientTest
{
	private static Logger log = LoggerFactory.getLogger(ClientTest.class);

	private static String cert = "/etc/cloudesire/certs/WindowsAzureKeyStore.jks";
	private static String password = "test123";
	private static String id = "7462339e-0ec5-49d6-842f-f49553bac47a";
	private static String windows = "bd507d3a70934695bc2128e3e5a255ba__RightImage-Windows-2008R2-x64-v14";
	private static String linux = "b39f27a8b8c64d52b05eac6a62ebad85__Ubuntu-12_04_3-LTS-amd64-server-20140130-en-us-30GB";

	private static String storageName = "scriptslol";
	private static String storageKey = "B/m8dJJduTB/LTmE81oFuNveXwNeHmZ8dzDSW0MsMnJ/E3qepgxLHsCvg2UpWOdyyBXw169wfCtw8zaMPSn02A==";
	private static String scriptURI = "https://scriptslol.blob.core.windows.net/scripts/test.ps1";
	private static String scriptFileName = "test.ps1";

	public static void main( String[] args ) throws Exception
	{
		KeyStore keyStore = getKeyStore(cert, password);
		final AzureClient client = new AzureClient(id, keyStore, password, null);

		//Test listImages
		final List<OSImage> images = client.getServiceClient().listImages();
		log.debug("Images: ");
		for(OSImage o : images)
		{
			log.debug(" OSImage: " + o);
		}

		// Test findImage
		final OSImage testOsImage = client.getServiceClient().findImage(windows);
		log.info("Test candidate: " + testOsImage);

		// Test listLocation
		// final List<Location> locations =
		// client.getConfigurationClient().listLocations();
		// for ( Location location : locations )
		// {
		// System.out.println(location);
		// }
		// Test listExtensionImages
		// List<ExtensionImage> extensionImages =
		// client.getServiceClient().listExtensionImages();
		// for (ExtensionImage ei : extensionImages)
		// {
		// System.out.println(ei.toString());
		// }
		// Test listResourceExtensions
		// List<ResourceExtension> resourceExtensions =
		// client.getServiceClient().listResourceExtensions();
		// for (ResourceExtension re : resourceExtensions)
		// {
		// System.out.println(re.toString());
		// }

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

		final String serviceName = UUID.randomUUID().toString();

		Deployment deployment = createVM(client, testOsImage, serviceName);

		attachDataDisk(client, serviceName, deployment);

		// stopAndStartTest(client, serviceName, deployment);

		// undeploy(client, serviceName);

	}

	private static Deployment createVM ( final AzureClient client, final OSImage testOsImage, final String serviceName )
			throws Exception,
			MalformedURLException
	{
		String accountJson = "{\"storageAccountName\":\"" + storageName + "\",\"storageAccountKey\": \"" + storageKey
				+ "\"}";
		String scriptJson = "{\"fileUris\": [\"" + scriptURI
				+ "\"], \"commandToExecute\":\"powershell -ExecutionPolicy Unrestricted -file " + scriptFileName
				+ "\"}";

		CloudService c = new CloudService();
		c.setServiceName(serviceName);
		c.setLabel(Base64.encode(c.getServiceName().getBytes()));
		c.setDescription(c.getServiceName());
		// c.setAffinityGroup("eu-cloudesire");
		c.setLocation("West Europe");
		final String cloudServiceID = client.getServiceClient().createCloudService(c);
		log.debug("Created CloudService: " + c + " ID: " + cloudServiceID);

		// Test Deploy Virtual Machine
		String name = "test-120";
		Deployment.Builder deploymentBuilder = new Deployment.Builder();
		Deployment deployment = deploymentBuilder
				.withName(name)
				.withHostname("testzzzzz")
				.withLabel(UUID.randomUUID().toString())
				.withMinCpu(2)
				.withMinDisk(50)
				.withMinMemory(2048)
				.withPassword("cicCio123")
				// askd123ASDASD1213
				.withSourceImage(testOsImage.getName())
				.withSourceImageLink(
						"http://" + storageName + ".blob.core.windows.net/communityimages/" + name
								+ UUID.randomUUID().toString() + ".vhd")
				.withUsername("manuel")
				.withOSFamily(OSFamily.Windows)
				.withAccountJson(accountJson)
				.withScriptJson(scriptJson)
				.build();

		deployment
				.getRoleList().iterator().next()
				.getConfigurationSets()
				.getConfigurationSets().iterator().next()
				.setDisableSshPasswordAuthentication(false);

		final String deploymentID = client.getServiceClient().createDeployment(deployment, serviceName);
		log.info("Waiting creation deployment ID: " + deploymentID);

		client.getOperationClient().waitForState(deploymentID, Status.SUCCEEDED, 5, TimeUnit.MINUTES);

		deployment = client.getServiceClient().getDeployment(serviceName, deployment.getName());
		log.info("Deployment created: " + deployment + " ID: " + deploymentID);

		return deployment;

	}

	private static void attachDataDisk ( final AzureClient client, final String serviceName,
			Deployment deployment ) throws Exception, MalformedURLException
	{
		log.info("Attaching disk...");
		DataVirtualHardDisk disk = new DataVirtualHardDisk();
		disk.setLogicalDiskSizeInGB(10);

		disk.setMediaLink("http://" + storageName + ".blob.core.windows.net/disks/data-" + UUID.randomUUID().toString()	+ ".vhd");
		String diskID = client.getServiceClient().addDataDisk(serviceName, deployment.getName(),
				deployment.getRoleInstanceList().getRoles().iterator().next().getRoleName(), disk);
		client.getOperationClient().waitForState(diskID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("Disk attached");
	}

	private static void stopAndStartTest ( final AzureClient client, final String serviceName, Deployment deployment )
			throws Exception, MalformedURLException
	{
		log.info("Shutdow now test!!");
		String shutupID = client.getServiceClient().stopMachine(serviceName, deployment.getName(),
				deployment.getRoleInstanceList().getRoles().iterator().next().getRoleName());
		log.info("Waiting...");
		client.getOperationClient().waitForState(shutupID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("TANGO DOWN !!");

		log.info("Start now test!!");
		String startID = client.getServiceClient().resumeMachine(serviceName, deployment.getName(),
				deployment.getRoleInstanceList().getRoles().iterator().next().getRoleName());
		log.info("Waiting...");
		client.getOperationClient().waitForState(startID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("READY !!");
	}

	private static void undeploy ( final AzureClient client, String serviceName ) throws Exception,
			MalformedURLException
	{
		log.info("Killing machine..");
		String killID = client.getServiceClient().destroyCloudService(serviceName, Boolean.TRUE);
		log.info("Waiting...");
		client.getOperationClient().waitForState(killID, Status.SUCCEEDED, 10, TimeUnit.MINUTES);
		log.info("DESTROYED UAHAU !!");
	}

	// http://gauravmantri.com/2013/08/25/consuming-windows-azure-service-management-api-in-java/
	// copy and paste
	private static KeyStore getKeyStore( String keyStoreName, String password ) throws IOException
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

		} catch ( Exception e )
		{
			e.printStackTrace();
		} finally
		{
			if ( fis != null )
			{
				fis.close();
			}
		}
		return ks;
	}
}
