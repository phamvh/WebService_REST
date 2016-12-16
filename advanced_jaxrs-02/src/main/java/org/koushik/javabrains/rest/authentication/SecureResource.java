package org.koushik.javabrains.rest.authentication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Demo for a secured resource.
 * Request to this resource needs to provide a user/password through Basic Authentication.
 * See SrecurityFilter for details.
 * @author van
 *
 */
@Path("secure")
public class SecureResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSecuredMessage(){
		return "This is a secured resource";
	}
}
