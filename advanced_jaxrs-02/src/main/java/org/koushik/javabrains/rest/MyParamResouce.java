package org.koushik.javabrains.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/test")
public class MyParamResouce {
	
	/**
	 *  //jaxrs injects values into these class-level variables
		//call:  localhost:8080/webapi/somePathParamValue/test?queryParam=hello
		 * 
		 * Note that you cannot  inject values into variables of a singleton because
		 * the singleton is normally created before request arrives.
	 */
		@PathParam("pathParam") private String pathParamExample;
		@QueryParam("queryParam") private String queryParamExample;
		
		@GET
		@Produces(MediaType.TEXT_PLAIN)
		public String testParams(){
			return pathParamExample +", "+queryParamExample;
		}
}
