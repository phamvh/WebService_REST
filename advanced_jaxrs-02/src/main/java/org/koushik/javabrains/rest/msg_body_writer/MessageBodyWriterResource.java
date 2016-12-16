package org.koushik.javabrains.rest.msg_body_writer;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("get_date")
public class MessageBodyWriterResource {
	
	/**
	 * This method returns an instance of Date, but jaxrs needs to return a String
	 * to any request (such as xml, json or just plain string). Therefore, we need to provide
	 * a Message Body Writer to jaxrs so it can use to convert Date to a string.
	 * 
	 * See DateMessageBodyWriter
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getDate(){
		return Calendar.getInstance().getTime();
	}

}
