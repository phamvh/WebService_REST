package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

/**
 * 
 * All HATEOAS related things are presented in this class, in the method 
 *      @GET
		@Path("/{messageId}")
		public Message getMessage(...)
		
		Note that I copied some of the code from koushik for comment-related classes, cuz I didn't do it in the earlier tutorial.
		But do not pay attention to that; they are not really related to how to create links.
 *
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages() {
		
		return messageService.getAllMessages();
	}

	
	/**
	 * Shows three different ways of creating links, depending on situations.
	 * For message and profile, the process is quite similar.
	 * It's much different for creating links for comments, due to subresource.
	 * 
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		
		//this is the most sophisticated due to the subresource forwarding.
		message.addLink(getUriForComments(uriInfo, message), "comments");
		
		return message;
		
	}

	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	/**
	 * 
	 * Creates a link for self.
	 * Note that we use getBaseUriBuilder, not the getAbsolutePath. Just an alternative.
	 * But in the method getUriForProfile, we have to use getBaseUriBuilder() in order to have /profiles
	 * in the URI, instead of  /messages, as the link is for a profile, not for a message.
	 * 
	 * */
	private String getUriForSelf(UriInfo uriInfo, Message message){
		return 
				uriInfo.getBaseUriBuilder()                        //  http://localhost:8080/REST10_HATEOAS/webapi
						.path(MessageResource.class)               //  /messages
						.path(Long.toString(message.getId()))      //  /1
						.build()                                   // builds an URI
						.toString();
								
	}
	
	/**
	 * 
	 *Creates a link for the profile of the user - the owner of this message
	 */
	private String getUriForProfile(UriInfo uriInfo, Message message){
		return 
		uriInfo.getBaseUriBuilder()                        //  http://localhost:8080/REST10_HATEOAS/webapi
				.path(ProfileResource.class)               //  /profiles
				.path(message.getAuthor())                 //  /koushik
				.build()                                   //  builds an URI
				.toString();
	}
	
	/**
	 *  Creates a link to the comments of a message.
	 *  The sophistication comes from the subresource. See below.
	 */ 
	private String getUriForComments(UriInfo uriInfo, Message message){
		return 
				uriInfo.getBaseUriBuilder()                        //  http://localhost:8080/REST10_HATEOAS/webapi
						.path(MessageResource.class)               //  /messages
						
						/**
						 *  Because Comment is a subresource, if we use .path(CommentResource.class),
						 *  we would only get "/". Therefore, we need to get the path from the MessageResource class as well,
						 *  which forwards the request to CommentResource.
						 *  
						 *  The method below gets the path annotation from a method, by taking the class and its method name.
						 */
						.path(MessageResource.class, "getCommentResource")       //  /{messageId}/comments 
						.path(CommentResource.class)                             //  add whatever remaining from the CommentResource class
						.resolveTemplate("messageId", message.getId())
						.build()                                   //  builds an URI
						.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
}
