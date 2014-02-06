package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.AzureClient;
import com.cloudesire.azure.client.apiobjects.OSImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

public class ClientTest
{
	private static Logger log = LoggerFactory.getLogger(ClientTest.class);

	private static String cert = "/home/manuelmazzuola/tmp/WindowsAzureKeyStore.jks";
	private static String password = "";
	private static String id = "";

	public static void main ( String[] args ) throws Exception
	{
		KeyStore keyStore = getKeyStore(cert, password);
		final AzureClient client = new AzureClient(id, keyStore, password, null);

		final OSImage testPackage = client.getConfigurationClient().findImage("b39f27a8b8c64d52b05eac6a62ebad85__Ubuntu-12_04_3-LTS-amd64-server-20140130-en-us-30GB");
		log.info("Test candidate: " + testPackage);

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
