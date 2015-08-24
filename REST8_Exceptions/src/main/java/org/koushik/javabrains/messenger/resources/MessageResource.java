package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService  = new  MessageService();
	
	/**
	 * This method is for demonstrating how to handle exception.
	 * For example, when someone enter an ID like 30003, no such message with that ID exists.
	 * We do not simply want to return null, like in previous projects. We want to throw an exception,
	 * such as DataNotFoundException.
	 * 
	 * Or, when someone enter an URL like http://url:8080/webapi/messi, we want to capture this as
	 * a generic exception, so that all exceptions, if have not been handled by specific exception like DataNotFoundException,
	 * will be handled by this generic one. 
	 * 
	 * Check out the method messageService.getMessage() in the MessageService class, which now may throw an exception
	 * if data is not found.
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessageById(@PathParam("messageId") long id){
		return messageService.getMessage(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessageInJson(){
		return messageService.getAllMessages();
	}
	

}
