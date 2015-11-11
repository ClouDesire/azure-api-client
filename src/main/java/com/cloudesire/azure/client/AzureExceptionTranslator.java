package com.cloudesire.azure.client;

import com.cloudesire.azure.client.apiobjects.ErrorResponse;
import com.cloudesire.tisana4j.ExceptionTranslator;
import org.apache.http.Header;
import org.apache.http.HttpStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AzureExceptionTranslator implements ExceptionTranslator
{
    @Override
    public AzureResponseException translateException( int responseCode, String responseMessage, String bodyMessage,
            ResponseMessage returnMessageRef, Header[] headers )
    {
        if ( responseCode == HttpStatus.SC_GONE ) return null;

        try ( InputStream stream = new ByteArrayInputStream( bodyMessage.getBytes() ) )
        {
            JAXBContext contextB = JAXBContext.newInstance( ErrorResponse.class );
            Unmarshaller unmarshallerB = contextB.createUnmarshaller();
            ErrorResponse errorResponse = (ErrorResponse) unmarshallerB.unmarshal( stream );

            return new AzureResponseException( responseCode, errorResponse.getMessage() );
        }
        catch ( JAXBException | IOException e )
        {
            return new AzureResponseException( responseCode, responseMessage );
        }
    }
}
