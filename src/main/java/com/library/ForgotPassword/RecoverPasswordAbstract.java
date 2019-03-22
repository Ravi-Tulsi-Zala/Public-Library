package com.library.ForgotPassword;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public abstract class RecoverPasswordAbstract {

	protected boolean emailSent = false;
	protected String subject;
	protected String body;
	protected String password;

	protected boolean getEmailSentStatus() {
		return emailSent;
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

	protected abstract void getEmailMatchingPassword();
	protected abstract boolean sendEmailToUser() throws AddressException, MessagingException, IOException;

}
