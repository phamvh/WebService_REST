package org.koushik.javabrains.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("test")
/**
 * By default, a new instance of this class is created everytime a request comes.
 * To override this default behavior, we can annotate this class as Singleton.
 * This way, only a single instance of this class is created.
 * And as a result, the variable count is shared across different requests.
 *
 */
@Singleton
public class MyResource {
	
	private int count = 0;
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod(){
		count++;
		return "This method has been called "+count+" times!";
	}
	

}
