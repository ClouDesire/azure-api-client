package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public class SerializationTest
{
	public static void main ( String[] args ) throws Exception
	{

		Images images = new Images();
		List<OSImage> list = new LinkedList<>();

		OSImage os = new OSImage();
		os.setName("pippo");
		OSImage ros = new OSImage();
		ros.setName("paolo");
		list.add(os);
		list.add(ros);

		images.setOsImages(list);


		JacksonXmlModule module = new JacksonXmlModule();
		module.setDefaultUseWrapper(false);
		XmlMapper mapper = new XmlMapper(module);

		mapper.writeValue(System.out, images);
	}
}
