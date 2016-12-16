package org.koushik.javabrains.rest.filters;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * A demo to show how to intercept with request or response.
 * See PoweredByResponseFilter for details.
 * @author van
 *
 */
@Path("testfilter")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod(){
		return "Hi filter. See headers for the new header called \"X-Powered By\"";
	}
	

}
