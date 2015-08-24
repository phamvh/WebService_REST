package org.koushik.javabrains.messenger.model;

public class ErrorMessage {
	
	private String errorMessage;
	private int errorCode;
	private String documentation;
	
	/**
	 * Note that if we only want JSON, then we do NOT need to put @XmlRootElement here. 
	 * JSON still works without having this annotation. I guess, this annotation is only for XML
	 * Koushik was wrong on this.
	 */
	public ErrorMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	

}
