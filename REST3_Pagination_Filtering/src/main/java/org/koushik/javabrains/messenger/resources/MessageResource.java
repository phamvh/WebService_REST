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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
/*
 * Defines the media type that the method of a resource class can accept. The json in the request body will be converted
 * to an object of type Message.
 */
@Consumes(MediaType.APPLICATION_JSON) 
/*
	Defines the media type that the method of a resource class can produce.
 */
@Produces(MediaType.APPLICATION_JSON) 
public class MessageResource {

	MessageService messageService  = new  MessageService();
	private static int count=0;
	
	/**
	 * Note that this constructor is called everytime a request is received.
	 * So, to be more efficient, one may consider making a pool of MessageService in order not to create too many
	 * of them - waste of memory.
	 */
	public MessageResource() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	/**
	 * Example shows how to capture the query parameters in the URL.
	 * Example: http://localhost:8080/messenger1/webapi/messages?year=2015&start=1&size=2
	 * 
	 * Note that this is different form path param (@PathParam), which is part of the
	 * URL, such as http://localhost:8080/messenger1/webapi/messages/1?year=2015
	 *   so 1 is the path param, not query param, while year is a query param.
	 * 
	 * @param year (for filtering) -  a possible parameter indicated in the query. If not, it will be 0
	 * @param start (for paginating) - a possible parameter indicated in the query. If not, it will be 0
	 * @param size  (for paginating) - a possible parameter indicated in the query. If not, it will be 0
	 * @return
	 */
	public List<Message> getMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size){
		if(year>0)
			return messageService.getAllMessagesForYear(year);
		if(start>0 && size > 0)
			return messageService.getAllMessagesPaginated(start, size);
		
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessageById(@PathParam("messageId") long id){
		Message message =  messageService.getMessage(id);
		
		//this is to test if constructor MessageResource() is called every time a request is received.
		message.setMessage(message.getMessage()+" - "+count++);
		return message;
	}
	
	@POST
	public Message addMessage(Message message){
		return messageService.addMessage(message);
		
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,  Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	
}
