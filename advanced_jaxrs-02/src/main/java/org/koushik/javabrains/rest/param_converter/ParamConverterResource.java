package org.koushik.javabrains.rest.param_converter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("date/{dateString}")
public class ParamConverterResource {
	
	/**
	 * In this method, we try to catch the dateString param as an instance of MyDate class.
	 * However, MyDate is a custom class, and jaxrs does not know how to convert it.
	 * Therefore, we need to provide a custom param converter in MyDateConverterProvider.
	 * 
	 * Supply a string "today, yesterday or tomorrow" for param {dateString}.
	 * 
	 * @param myDate
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDate(@PathParam("dateString") MyDate myDate){
		return myDate.toString();
		
	}

}
