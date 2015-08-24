package org.koushik.javabrains.messenger.resources;


import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Show three different ways of obtaining params from the request.
 * @author van
 *
 */
@Path("/params")
@Consumes(MediaType.TEXT_PLAIN) 
@Produces(MediaType.TEXT_PLAIN) 
public class InjectParamResource {
	
	@GET
	/**
	 * FIRST WAY - most tedious way
	 * Demonstrate how to get other types of params, such as matrix param, header or cookie.
	 * 
	 * Matrix param does not seem popular: it is separated from the emain URL by a semi colon: http://url.com?term=google
	 * 
	 * One can use @FormParam to get the values sent from a form. However, this is REST API so form is not that popularly used.
	 * Form is mostly used in regular webapp.
	 * 
	 * @param searchTerm
	 * @param customHeader
	 * @param sessionId
	 * @return
	 */
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("term") String searchTerm, 
									@HeaderParam("customHeader") String customHeader,
									@CookieParam("sessionId") String sessionId){
		return "searchTerm = "+searchTerm + ", customHeader=" + customHeader+ ", cookie="+sessionId;
	}
	
	/**
	 * A shorter way of getting the params is to use context.
	 * We can use either (or both) of the types UriInfo and HttpHeaders to capture information from the request.
	 * 
	 * UriInfo contains information about the URL, such as absolute path, path parameters.
	 * 
	 * HttpHeaders contains headers and cookies, etc.
	 * 
	 */
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										  @Context HttpHeaders httpHeaders){
		
		return "abs path: "+uriInfo.getAbsolutePath() +", cookies = " + httpHeaders.getCookies().toString();
		
	}
	
	@GET
	@Path("/beans")
	public String getParamsUsingFilterBean(@BeanParam MessageFilterBean filterBean){
		
		return "year=: "+filterBean.getYear()+", start="+filterBean.getStart()+", size="+filterBean.getSize();
	}
	
	

}
