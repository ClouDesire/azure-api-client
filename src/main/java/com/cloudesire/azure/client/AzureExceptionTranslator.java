package com.cloudesire.azure.client;

import com.cloudesire.tisana4j.ExceptionTranslator;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class AzureExceptionTranslator implements ExceptionTranslator
{
	@Override
	public Exception translateError ( int responseCode, String responseMessage, InputStream errorStream )
	{
		//System.out.println(convertStreamToString(errorStream));
		// Handle 410 Gone for deleted VMs.
		if (responseCode == HttpStatus.SC_GONE) return null;
		try
		{
			JacksonXmlModule module = new JacksonXmlModule();
			module.setDefaultUseWrapper(false);
			XmlMapper xmlMapper = new XmlMapper(module);
			ErrorResponse errorResponse = xmlMapper.readValue(errorStream, ErrorResponse.class);
			return new AzureResponseException(errorResponse.getCode(), errorResponse.getMessage());

		} catch (IOException e)
		{
			return new AzureResponseException(Integer.toString(responseCode), responseMessage);
		}

	}

}
