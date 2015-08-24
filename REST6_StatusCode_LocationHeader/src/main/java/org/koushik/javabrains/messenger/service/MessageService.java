package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
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
		/*
		Message m1 = new Message(1L, "Hello World", "Koushik");
		Message m2 = new Message(1L, "Hello Jersey", "Koushik");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		return list;
		*/
		
	}
	
	public Message getMessage(long id){
		return messages.get(id);
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
