package org.koushik.javabrains.rest.authentication;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

/**
 * A filter that interferes with every request, whose path contains "secure".
 * The filter checks if correct user/password is provided.
 * 
 * Note that Basic Auth is used where user:password is encoded in Base 64 in  header called Authorization
 * 
 *   header name: "Authorization"
 *   header value: "Basic adsjKIjkIjjos==", which is the encode of "user:password" in Base64
 * 
 *
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter{

	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secure";  //the path to the resource (see class SecureRFesrouce.java)
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getUriInfo().getPath());
		System.out.println(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX));
		if(!requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){
			return; //if the resource is not "secure", then let it go through. We only protect the resource with the path "secure"
		}else{
			System.out.println("Authorizing process ...");
			System.out.println(requestContext.getHeaders());
			List<String> authHeader= requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if(authHeader != null  &&  authHeader.size() > 0){
				String authToken = authHeader.get(0);
				//Get rid of the "Basic " part
				authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
				
				System.out.println("64 Encoded = "+authToken);
				
				//Decode the encoded "user:password"
				String decodedString = Base64.decodeAsString(authToken);
				
				System.out.println("Decoded = "+decodedString);
				
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				
				String userName = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				if("user".equals(userName) && "password".equals(password)){
					return; //this means we let the request pass the filter
				}
					
			}
			//if the method has not returned by now, then login is not correct or header is missing
			
			Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access the resource")
					.build();
			
			//abort the request and return an unauthorized response
			requestContext.abortWith(unauthorizedStatus);
			
		}
		
	}

}
