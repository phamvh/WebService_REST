package org.koushik.javabrains.messenger.model;

/**
 * This is the links related to a message, such as self link, update link, etc.
 * In the Message class, there is now a field links - a list of links for the message.
 * @author van
 *
 */
public class Link {

	private String link;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	private String rel;
}
