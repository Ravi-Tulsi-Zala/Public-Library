package com.library.borrowItem;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.businessModels.UserItem;
import com.library.email.EmailDetails;
import com.library.email.SendEmail;

public class BookingEmailSender {

	EmailDetails emailDetails;
	private static final Logger logger = LogManager.getLogger(BookingEmailSender.class);
	
	public void sendEmail(UserItem item) {

		String title = item.getTitle();
		String email = item.getEmail();
		String category = item.getCategory();
		EmailDetails emailDetails = new EmailDetails();

		emailDetails.setSubject("Reg : "+category+" titled " + title + " is borrowed for you!!");
		emailDetails.setBody("Dear " + email + " ,<br/><br/>" + "This is to notify you that the "+category+"  titled " + title
				+ " is booked for you!" + "<br/><br/>" + "Regards, " + "<br/>" + " Public Library.");
		emailDetails.setUserEmailID(email);
		try {
			SendEmail.sendmail(emailDetails);
		} catch (MessagingException | IOException e) {

			logger.log(Level.ALL, "Check Email Sender class!", e);
		}

	}
}
