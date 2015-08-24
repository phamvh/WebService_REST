package org.koushik.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This annotation is for converting a Message object to XML. 
 * Check out the get messages methods in the MessageResource class, where a method may return a list of Message objects.
 * This will help convert each of such object into XML, and use this as the root element.
 * 
 * Note that this is NOT needed when the response type is JSON.
 */
@XmlRootElement
public class Message {
	
	public Message(){
		
	}
	
	public Message(long id, String message, String author) {
	
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}
	private long id;
	private String message;
	private Date created;
	private String author;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	

}
