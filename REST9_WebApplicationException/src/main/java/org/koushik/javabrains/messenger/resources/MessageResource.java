package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.koushik.javabrains.messenger.model.ErrorMessage;
import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService  = new  MessageService();
	
	/**
	 * This method is for demonstrating how to handle exception by using WebApplicationException.
	 * As compared to the previous project (REST8_Exceptions), here we use the exceptions that jax-rs is already aware of.
	 * Therefore, we do NOT need to create any mapper nor give any provider.
	 * 
	 * WebApplicationException has several child and grand-child classes: 
	 *     WebApplicationException -> ClientErrorException -> NotFoundException.
	 *     
	 * Note that if we copy code from the previous project here, we need to disable the GenericExceptionMapper, otherwise all
	 * will be mapped to that mapper, and none will be handled by these specific exceptions. Simply comment out the @Provider
	 * to disable the mapper.
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessageById(@PathParam("messageId") long id){
		Message message = messageService.getMessage(id);
		if(id<0)
			//simplest way
			throw new WebApplicationException(Status.NOT_FOUND);
		if(message == null){
			//more sophisticated - here we set info, code and documentation
			ErrorMessage errorMessage = new ErrorMessage("Data not found", 404, "http://javabrains.koushik.org");
			
			throw new NotFoundException(Response.status(Status.NOT_FOUND)
										.entity(errorMessage)
										.build());
		   
		}
		return message;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessageInJson(){
		return messageService.getAllMessages();
	}
	

}
