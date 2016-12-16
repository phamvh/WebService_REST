package org.koushik.javabrains.rest.rest_client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Just another handy way of using Client through Invocation
 *
 */
public class InvocationClient {

	public static void main(String[] args) {
		Invocation invocation = prepareRequest("12/10/2015");
		Response response = invocation.invoke();
		System.out.println(response.getStatus());

	}
	
	/**
	 * Do NOT call this method; it is for demo only
	 * because URI is fake.
	 * 
	 * date can be "today", "tomorrow" or "yesterday"
	 * @param date
	 * @return
	 */
	public static Invocation prepareRequest(String date){
		Client client = ClientBuilder.newClient();
		return client.target("some.URI/")
				.path("messages")
				.queryParam("someParam", 1234)
				.request(MediaType.APPLICATION_JSON)
				.buildGet();
	}

}
