package org.koushik.javabrains.rest.msg_body_writer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

//Make sure to mark this as a provider
@Provider
//Tell jaxrs that this provider can only provide plain text.
//you can have multiple message body writers to supply different media types, such as json or xml.
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyWriter implements MessageBodyWriter<Date>{

	/**
	 * jaxrs will simply asks every provider by using this method that is an instance of class "type"
	 * writeable; if yes, then jaxrs will use the method writeTo; if no, jaxrs will look further at other providers.
	 */
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Date.class.isAssignableFrom(type);
	}

	/**
	 * With this method, jaxrs provides an instance of Date for yo to convert to String, and then
	 * write that string to the output stream provided.
	 */
	@Override
	public void writeTo(Date t, //in this simple example, we only pay attention to this arg, which is an instance of Date
			Class<?> type, 
			Type genericType, 
			Annotation[] annotations, 
			MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, 
			OutputStream entityStream)  //this is the output stream to write the conversion of Date to String to.
					throws IOException, WebApplicationException {
		
		entityStream.write(t.toString().getBytes());
	}
	
	/**
	 * This method is currently deprecated. It is recommended to return -1 here.
	 */
	@Override
	public long getSize(Date t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		//return 0;
		return -1;
	}

}
