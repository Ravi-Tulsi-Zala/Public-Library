package com.library.signIn;

import java.io.IOException;

import javax.mail.MessagingException;

import com.library.email.EmailUtility;

public class ForgotPassword extends ForgotPasswordAbstract {
	public String securityQuestion;
	public String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public boolean sendEmailToUser() {
		try {
			String emailID = getEmail();
			String password = getPassword();
			String subject = getSubject();
			String body = getBody();
			EmailUtility.sendmail(emailID, password, subject, body);
			emailSent = true;
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		return emailSent;
	}

}
