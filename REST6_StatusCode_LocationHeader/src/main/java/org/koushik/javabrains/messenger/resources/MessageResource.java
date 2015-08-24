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
	 * If we return a Message object, we won't be able to set other params
	 * for the response, such as headers and status code (which is set by jersey).
	 * 
	 * Now, if we want to customize the response better, we need to return a Response object, 
	 * which encapsulate all such information, such as status code, headers. An imporant header
	 * for following HEOTOS is to have a header called Location, which is the URL to access the
	 * message which is created in this method.
	 * @throws URISyntaxException 
	 * 
	 */
	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message) {
		//we need to take a hold on the returned message from messageService because it has ID. The one in the argument comes from the request, and ID has not been created for it yet.
		Message newMessage = messageService.addMessage(message);
		
		//Third method: use @Context to get the information about URL, instead of hard coding it.
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId) //the path() concatenate string to the path;
												  .build(); // build() builds the URI.
		
		return Response.created(uri)
						.entity(newMessage)
						.cookie(new NewCookie("Server Code", "Java"))
						.build();
		
		/*// Method 2: add cookie and Location Header to the response. Location header (URI) is still hard coded.
		return Response.created(new URI("/REST6_StatusCode_LocationHeader/webapi/messages/"+newMessage.getId()))
				.entity(newMessage)
				.cookie(new NewCookie("Server Code", "Java"))
				.build();
		*/
		
		/*// Method 1 - simple, only for setting status code.
		return Response.status(Status.CREATED)
						.entity(newMessage)
						.build();
						*/
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessageInJson(){
		return messageService.getAllMessages();
	}
	
	
	

}
