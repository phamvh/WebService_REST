package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * Note that this @Path is optional.
 * It is empty because it is inferred from the MessageResource class, specifically
 * from method public CommentResource getCommentResource(), which forwards the requests
 * to this class.
 * 
 * Note that all the path of the URL http://url:8080/messages/1/comments has been received in MessageResource.
 * The @Path in this class and its methods will simply the continuation of the above path.
 *
 */
@Path("/")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class CommentResource {

	/**
	 * 
	 * This method accepts all GET requests of pattern http://url:8080/messages/1/comments
	 */
	@GET
	public String getAllComments(){
		return "Sample comments";
	}
	
	/**
	 * 
	 * This method accepts all GET requests of pattern http://url:8080/messages/1/comments/10
	 * Here we can get all the path params, even those that are related to messages, like messageId, because they are all forwarded here
	 * to be handled by this class.
	 */
	@GET
	@Path("/{commentId}")
	public String getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		return "messageId="+messageId+", commentId="+commentId;
	}
}
