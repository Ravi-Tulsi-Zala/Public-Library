package com.library.ForgotPassword;

public class EmailDetails {
	private String userEmailID;
	private String adminEmailID;
	private String adminPassword;
	private String body;
	private String subject;
	
	public void setAdminEmailID(String adminEmailID) {
		this.adminEmailID = adminEmailID;
	}
	public String getAdminEmailID() {
		return adminEmailID;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBody() {
		return body;
	}
	public void setUserEmailID(String userEmailID) {
		this.userEmailID = userEmailID;
	}
	public String getUserEmailID() {
		return userEmailID;
	}
	
}
