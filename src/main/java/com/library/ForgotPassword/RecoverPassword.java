package com.library.ForgotPassword;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IUserDAO;
import com.library.validatations.ValidateUserFormsAbstract;

public class RecoverPassword extends RecoverPasswordAbstract {
	private String securityQuestion;
	private String securityQuestionAnswer;
	private String email;
	EmailDetails details = null;
	private static final Logger logger = LogManager.getLogger(RecoverPassword.class);

	public RecoverPassword() {
		details = new EmailDetails();
	}

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

	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}

	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public boolean sendEmailToUser() throws AddressException, MessagingException, IOException {
		details.setUserEmailID(email);
		details.setBody(getBody());
		details.setSubject(getSubject());
		getEmailMatchingPassword();
		EmailUtility.sendmail(details);
		emailSent = true;
		return emailSent;
	}

	@Override
	protected void getEmailMatchingPassword() {
		DAOFactory factory = new DAOFactory();
		IUserDAO user = factory.makeUserDAO();
		details.setSubject("LMS reminder for password.");
		details.setBody(user.getEmailRelatedPassword(details.getUserEmailID()).replace(ValidateUserFormsAbstract.saltValue,""));;
	}

}
