package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.apiobjects.Images;
import com.cloudesire.azure.client.apiobjects.OSImage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

public class SerializationTest
{
	public static void main( String[] args ) throws Exception
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

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JAXBContext context = JAXBContext.newInstance(Images.class);
		Marshaller m = context.createMarshaller();
		m.marshal(images, baos);

		System.out.println(baos.toString());
	}
}
