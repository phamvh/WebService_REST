package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
/**
 * If all the methods in the classes either produce or consume (or both) application/json, then we can move the two 
 * annotations here to the class level to reduce the length of the code. These annotations will apply to all the methods
 * in the class.
 * Like this:
 * 
 * 	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
 *
 */
public class MessageResource {
	
	MessageService messageService  = new  MessageService();
	
	/**
	 * this is to get the response as json.
	 * Note that we need to uncomment one tag in the pom.xml in order for the json to work.
	 *    <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-moxy</artifactId>
          </dependency>
	 * 
	 * Uncommenting this tag will make maven to automatically download additional jars.
	 * The annotation @XmlRootElement in the Message class is NOT needed for JSON. It can still sit there, but it does nothing
	 * in case of json.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessageInJson(){
		return messageService.getAllMessages();
	}
	
	/**
	 * Post a new message
	 * Note that the @Consumes automatically binds the request body (a json text) into a Message object, and pass
	 * to this method as an argument. Is this real?
	 * 
	 * To run this, in postman, we need to:
	 * 1) set the Content_type to "application/json"
	 * 2) For the body of the request, choose "raw". The type "application/json" shown in the list, because postman
	 * automatically infers from step 1).
	 * 3) For the body. use this (without giving an id to the new message b/c the program will assign it automatically):
	 * 
	 *    {
		    "author": "Koushik",
		    "created": "2015-08-17T22:33:04.849",
		    "message": "Hello World 2"
 		  }
 		  
 		  
	 * 
	 * 
	 * @param message
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return messageService.addMessage(message);
	}
	
	/**
	 * Very similar to POST
	 * The only difference is because we specify the id of the message in the URL to be updated,
	 * therefore we have to capture it here by using annotation @PathParam.
	 * 
	 * The json text in the request body will be converted to a Message object (without any ID). Therefore,
	 * in here, we assign the ID (given in the URL) to the object (given in the body request), and update.
	 */
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	/**
	 * Similar to POST and PUT
	 * We don't need any request body in this case. ID is given in the URL.
	 * We also do not need to return anything.
	 * @param id
	 */
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	

	/**
	 * This is to get the response as xml.
	 * Note that in the Message class, we need to put the annotation @XmlRootElement, because
	 * this tells jax that convert the instance of this class to XML by using the fields (and getters and setters).
	 * Without this annotation in the Message class, the server will return 500.
	 * @return
	 */
	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessageInXml(){
		return messageService.getAllMessages();
	}
	
	/**
	 * This is an example of path name "test', which is a subpath of 'messages'.
	 * This is called by calling:
	 *     http://localhost:8080/messenger/webapi/messages/test
	 * @return
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "test";
	}
	
	/**
	 * shows how to get a parameter from the URL pat nameh.
	 * Braces are used to capture the url path name.
	 * @param messageId
	 * @return
	 */
	@GET
	@Path("/test/{messageId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String test2(@PathParam("messageId") String messageId){
		return "Got path param: "+messageId;
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessageById(@PathParam("messageId") long id){
		return messageService.getMessage(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
