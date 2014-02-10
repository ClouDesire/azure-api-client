package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.ErrorResponse;
import com.cloudesire.tisana4j.ExceptionTranslator;
import org.apache.http.HttpStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
			JAXBContext contextB = JAXBContext.newInstance(ErrorResponse.class);
			Unmarshaller unmarshallerB = contextB.createUnmarshaller();
			ErrorResponse errorResponse = (ErrorResponse) unmarshallerB.unmarshal(errorStream);
			return new AzureResponseException(errorResponse.getCode(), errorResponse.getMessage());
		} catch (JAXBException e)
		{
			return new AzureResponseException(Integer.toString(responseCode), responseMessage);
		}
	}

}
