package com.cloudesire.azure.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpStatus;

import com.cloudesire.azure.client.apiobjects.ErrorResponse;
import com.cloudesire.tisana4j.ExceptionTranslator;

public class AzureExceptionTranslator implements ExceptionTranslator
{
	@SuppressWarnings ( "unchecked" )
	@Override
	public AzureResponseException translateException ( int responseCode, String responseMessage, String bodyMessage,
			ResponseMessage returnMessageRef )
	{
		if (responseCode == HttpStatus.SC_GONE) return null;

		try (InputStream stream = new ByteArrayInputStream(bodyMessage.getBytes()))
		{
			JAXBContext contextB = JAXBContext.newInstance(ErrorResponse.class);
			Unmarshaller unmarshallerB = contextB.createUnmarshaller();
			ErrorResponse errorResponse = (ErrorResponse) unmarshallerB.unmarshal(stream);
			
			return new AzureResponseException(Integer.parseInt(errorResponse.getCode()), errorResponse.getMessage());
		} catch (JAXBException | IOException e)
		{
			return new AzureResponseException(responseCode, responseMessage);
		}
	}

}
