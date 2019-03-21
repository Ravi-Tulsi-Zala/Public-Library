package com.library.signIn;

public abstract class ForgotPasswordAbstract {

	protected boolean emailSent = false;
	protected String subject;
	protected String body;
	protected String password;
	protected String email;

	protected boolean getEmailSentStatus() {
		return emailSent;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	protected abstract boolean sendEmailToUser();

}
