package org.koushik.javabrains.rest.custom_mediatype;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("shortdate")
public class ShortDateResource {
	/**
	 * This method produces a custom media type "shortdate". The usage is normally when you have multiple versions v1, v2...
	 * and you want to provide different result for each version. Also, sometimes you want some media type like csv, which
	 * jaxrs does not have out of the box.
	 * 
	 * jaxrs doesn't not know to handle a new custome media type.
	 * Therefore, we need to provide a MessageBodyWriter and a MessageBodyReader for this new custom media type.
	 * 
	 * See ShortDateMessageBodyWriter for details.
	 * 
	 * Also, see the comment to method getDateTextPlain() below. 
	 * 
	 */
	@GET
	@Produces("text/shortdate")
	public Date getDateShortFormat(){
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * This is a regular media type.
	 * Now we have two methods corresponding to one uri "shortdate". which method to be called depending 
	 * on the media type in the header;
	 * If in the header for Accept, we put "text/plain", then getDateTextPlain() will serve the request.
	 * If we put "text/shortdate", then getDateShortFormat() will serve the request.
	 * 
	 * Note that if we do not specify the header "Accept", then getDateShortFormat() is called be default. Looks like
	 * because it appears first in the class.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getDateTextPlain(){
		return Calendar.getInstance().getTime();
	}


}
