package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;

public class MessageService {
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		Message m1 = new Message(1L, "Hello World", "Koushik");
		Message m2 = new Message(2L, "Hello Jersey", "Koushik");
		messages.put(1L, m1);
		messages.put(2L, m2);
		
	}
	
	
	public List<Message> getAllMessages(){
		
		return new ArrayList<Message>(messages.values());
		
	}
	
	/**
	 * Note that this method now may throws an exception.
	 * When this exception happens, jax-rs will look for a handler for this particular exception by looking
	 * into the providers (@Provider) that have been registered with jax-rs. Such handler is registered by implelenting
	 * interface ExceptionMapper (see DataNotFoundExceptionMapper), and annotated with @Provider.
	 */
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null)
			throw new DataNotFoundException("Message with id "+id+" not found.");
		else
			return message;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	public Message updateMessage(Message message){
		if(message.getId() <= 0)
			return null;
		
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
