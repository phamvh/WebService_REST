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
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON) 
public class MessageResource {

	MessageService messageService  = new  MessageService();
	private static int count=0;
	
	
	public MessageResource() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This function causes all requests of pattern http://url:8080/messages/1/comments to be forwarded to CommentResource for 
	 * further handling.
	 * 
	 * Note that /comments is a subresource of /messages. All these subresources get forwarded to CommentResource.
	 * 
	 * Note that we do not specify the method (get, post...), therefore, requests of all types of methods of this URL pattern
	 * will be forwarded. CommentResource will handle all of these.
	 * @return
	 */
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	@GET
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
