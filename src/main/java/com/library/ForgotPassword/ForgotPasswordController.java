package com.library.ForgotPassword;

import java.io.IOException;
import javax.mail.MessagingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ForgotPasswordController implements IForgotPasswordController {

	private boolean status = false;
	private static final Logger logger = LogManager.getLogger(ForgotPasswordController.class);
	private RecoverPassword recoverDetails = null;

	public ForgotPasswordController(RecoverPassword recoverDetails) {
		this.recoverDetails = recoverDetails;
	}

	public boolean recoverPassword(){
		try {
			if (isAnswerCorrect()) {
				RecoverPassword recoverPassword = new RecoverPassword();
				recoverPassword.setEmail(recoverDetails.getEmail());
				recoverPassword.setSecurityQuestionAnswer(recoverDetails.getSecurityQuestionAnswer());
				recoverPassword.setSecurityQuestion(recoverDetails.getSecurityQuestion());
				status = recoverPassword.sendEmailToUser();
				logger.log(Level.ALL, "Email sent successfully! => ",status);
			}
		} catch (MessagingException | IOException e) {
			logger.log(Level.ALL, "Unable to send email.", e);
		}
		return status;
	}

	public String setQuestion() {
	    double x = (int)(Math.random()*10)+0;
		recoverDetails.setSecurityQuestion(Double.toString(x));
		return recoverDetails.getSecurityQuestion();
	}

	public String getQuestion() {
		return recoverDetails.getSecurityQuestion();
	}

	private boolean isAnswerCorrect() {
		if (recoverDetails.getSecurityQuestionAnswer().equals(recoverDetails.getSecurityQuestion())) {
			return true;
		} else {
			return false;
		}
	}
}
