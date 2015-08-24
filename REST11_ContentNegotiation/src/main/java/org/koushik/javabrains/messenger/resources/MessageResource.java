package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.print.attribute.standard.Media;
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

/**
 * Content negotation: Users requests contents of specific type by indicating the "Accept" header.
 * 
 * Example:
 *  Accept=application/json
 *  
 *  or 
 *  
 *  Accept=text/xml
 * 
 * Demonstrate how requests of the same URL, but different content type (application/json or text/xml)
 * are mapped to different methods. See two method below (same paths, just different Media type).
 * 
 * 
 * ANOTHER SIMPLER WAY:
 * If both xml and json can come out of the box form jax-rs (and it looks like always so), we can actually have only one method for requests that only differ
 * in content type. To do this, simply keep only one method below, and put the annotations:
 * 
 * 
 *    @Produce(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 *    
 *    that's it.
 *
 *
 */
@Path("/messages")
public class MessageResource {
	
	MessageService messageService  = new  MessageService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesInJson(){
		return messageService.getAllMessages();
	}

	@GET
	@Produces(MediaType.TEXT_XML) 
	public List<Message> getMessagesInXml(){
		return messageService.getAllMessages();
	}
	

}
