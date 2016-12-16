package org.koushik.javabrains.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * This is a filter that will interfere with every response by just adding a new header to it.
 * This will interfere with all the responses, even those created in the other packages. 
 * In other word, this filter has a global effect on the project, not just to the package it belongs to.
 * 
 * Try testing this on Postman with two different DRIs:
 *   http://localhost:8080/advanced-jaxrs-02/webapi/test
 *   http://localhost:8080/advanced-jaxrs-02/webapi/testfilter
 *   
 *  You will see a new header called "X-Powered-By" in every response.  
 */
@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		//add a new header to every response
		responseContext.getHeaders().add("X-Powered-By", "Google");
	}



}
