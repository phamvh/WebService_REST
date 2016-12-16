package org.koushik.javabrains.rest.rest_client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

	public static void main(String[] args) {
		clientDemo();
		
		lessElegantClinetDemo();

	    //moreElegantClientDemo();
		
	}

	/**
	 * Do not call this method;
	 * it is just for demo purposes because the URI is fake. 
	 * Only the URI is fake; everything else is real.
	 */
	private static void moreElegantClientDemo() {
		//// A more elegant way
		
		////Note that this code will not work because I just put a fake URI for demo.
		
		System.out.println("\n\nA more elegant way to using Client:");
		Client client2 = ClientBuilder.newClient();
			
		WebTarget baseTarget = client2.target("some.url/webapi");
		WebTarget messagesarget = baseTarget.path("messages"); //add "messages to the path of the URI
		WebTarget singleMessageTarget = messagesarget.path("{messageId}");
			
		String message1 = singleMessageTarget
					.resolveTemplate("messageId", 1) //replace template with ID 1
					.request(MediaType.APPLICATION_JSON)
					.get(String.class);
			
		System.out.println(message1);
		
		String message2 = singleMessageTarget
				.resolveTemplate("messageId", 2)   //replace template with ID 2
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		
	    System.out.println(message2);
	}

	private static void lessElegantClinetDemo() {
		///// A less elegant way
		
		System.out.println("\n\nA less elegant way to using Client:");
		Client client1 = ClientBuilder.newClient();
		
		Response response = client1
				.target("http://localhost:8080/advanced-jaxrs-02/webapi/shortdate")  //supply a URI
				.request(MediaType.TEXT_PLAIN)        //provide a media type (optional)
				.get();   
		
		String result = response.readEntity(String.class);
		System.out.println(result);
	}

	private static Client clientDemo() {
		System.out.println("REST Cient API Demo");
		
		//Create a new instance of a REST client
		Client client = ClientBuilder.newClient();
		
		String responseString = client
				.target("http://localhost:8080/advanced-jaxrs-02/webapi/shortdate")  //supply a URI
				.request(MediaType.TEXT_PLAIN)        //provide a media type (optional)
				.get(String.class);                  //Provide a type so that the result of .get() is of that type.
		                                             //If not type is provided, then .get() return an instance of Response.

		System.out.println(responseString);
		return client;
	}

}
