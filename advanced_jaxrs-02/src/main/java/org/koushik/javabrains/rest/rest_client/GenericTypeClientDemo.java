package org.koushik.javabrains.rest.rest_client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Demo: suppose your REST API returns a JSON, which is a List of Message(s)
 * How do you handle this case?
 * 
 * This does not work automatically out of the box, so you need to
 * twist it a little bit by using GenericType class.
 *
 */
public class GenericTypeClientDemo {

	/**
	 * Do NOT run this. This is for demo only because
	 * the URI is fake.
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		List<Message> messages = client.target("some.url")
				.path("messages")
				.queryParam("someParam", "someValue")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>(){});
		
		System.out.println(messages);

	}
	
	static class Message{
		//some bean class
	}

}
