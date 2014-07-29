package com.cloudesire.azure.client;

import com.cloudesire.tisana4j.ExceptionTranslator;
import com.cloudesire.tisana4j.exceptions.RestException;

public class AzureExceptionTranslator implements ExceptionTranslator
{
	@Override
	public <T extends RestException> T translateException ( int responseCode, String responseMessage,
			String bodyMessage, ResponseMessage returnMessageRef )
	{
//		if ( responseCode == HttpStatus.SC_GONE ) return null;
//		try
//		{
//			JAXBContext contextB = JAXBContext.newInstance(ErrorResponse.class);
//			Unmarshaller unmarshallerB = contextB.createUnmarshaller();
//			ErrorResponse errorResponse = (ErrorResponse) unmarshallerB.unma(responseMessage);
//			return new AzureResponseException(Integer.parseInt(errorResponse.getCode()), errorResponse.getMessage());
//		} catch ( JAXBException e )
//		{
//			return new AzureResponseException(Integer.toString(responseCode), responseMessage);
//		}
		return null; // XXX proper error handling #2732
	}

}
