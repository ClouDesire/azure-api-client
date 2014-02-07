package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.AzureClient;
import com.cloudesire.azure.client.apiobjects.CloudService;
import com.cloudesire.azure.client.apiobjects.ConfigurationSet;
import com.cloudesire.azure.client.apiobjects.ConfigurationSets;
import com.cloudesire.azure.client.apiobjects.Deployment;
import com.cloudesire.azure.client.apiobjects.Location;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.cloudesire.azure.client.apiobjects.OSVirtualHardDisk;
import com.cloudesire.azure.client.apiobjects.Role;
import com.cloudesire.azure.client.apiobjects.RoleList;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
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
		for(Location location : locations)
		{
			System.out.println(location);
		}

		// Create CloudService
		CloudService c = new CloudService();
		c.setServiceName(UUID.randomUUID().toString());
		c.setLabel(Base64.encode(c.getServiceName().getBytes("UTF-8")));
		c.setDescription(c.getServiceName());
		c.setLocation(locations.iterator().next().getName());
		final CloudService cloudService = client.getServiceClient().createCloudService(c);
		log.info("Requested CloudService: " + c + " Response CloudService: " + cloudService);

		// Test Deploy Virtual Machine
		ConfigurationSet set = new ConfigurationSet();
		set.setDisableSshPasswordAuthentication(false);
		set.setHostName("testhostname");
		set.setUserName("cloudesire");
		set.setUserPassword("cloudesireCl0udesire");

		List<ConfigurationSet> cs = new ArrayList<>();
		cs.add(set);

		ConfigurationSets configurationSets = new ConfigurationSets();
		configurationSets.setConfigurationSets(cs);

		OSVirtualHardDisk osvh = new OSVirtualHardDisk();
		osvh.setSourceImageName(testOsImage.getName());
		osvh.setMediaLink("http://test123123.blob.core.windows.net/communityimages/"+ UUID.randomUUID().toString() +".vhd");

		Role role = new Role();
		role.setRoleName(UUID.randomUUID().toString());
		role.setConfigurationSets(configurationSets);
		role.setOsVirtualHardDisk(osvh);
		role.setRoleSize("ExtraSmall");

		List<Role> roles = new ArrayList<>();
		roles.add(role);

		RoleList roleList = new RoleList();
		roleList.setRoles(roles);

		Deployment deployment = new Deployment();
		deployment.setName("Test");
		deployment.setDeploymentSlot("Production");
		deployment.setLabel("Label");
		deployment.setRoleList(roleList);

		final Deployment deployment1 = client.getServiceClient().createDeployment(deployment, cloudService.getServiceName());
		log.info("Deployment : " + deployment1);
	}

	// http://gauravmantri.com/2013/08/25/consuming-windows-azure-service-management-api-in-java/
	// copy and paste
	private static KeyStore getKeyStore(String keyStoreName, String password) throws IOException
	{
		KeyStore ks = null;
		FileInputStream fis = null;
		try {
			ks = KeyStore.getInstance("JKS");
			char[] passwordArray = password.toCharArray();
			fis = new java.io.FileInputStream(keyStoreName);
			ks.load(fis, passwordArray);
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				fis.close();
			}
		}
		return ks;
	}
}
