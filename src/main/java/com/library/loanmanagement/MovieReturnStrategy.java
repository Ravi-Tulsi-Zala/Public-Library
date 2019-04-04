package com.library.loanmanagement;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.ForgotPassword.ForgotPasswordController;
import com.library.IDAO.IMovieDAO;
import com.library.IDAO.IUserItemDAO;
import com.library.businessModels.UserItem;
import com.library.email.EmailDetails;
import com.library.email.EmailUtility;

public class MovieReturnStrategy implements IReturnItemStrategy {

	IDAOFactory iDAOfactory;
	IMovieDAO iMovieDAO;
	IUserItemDAO iUserItemDAO;
	EmailDetails emailDetails;
	UserItem userOnHold;
	private static final Logger logger = LogManager.getLogger(ForgotPasswordController.class);
	
	public MovieReturnStrategy() {
		
		iDAOfactory = new DAOFactory();
		iMovieDAO = iDAOfactory.makeMovieDAO();
		iUserItemDAO = iDAOfactory.makeUserItemDAO();
		emailDetails = new EmailDetails();
		
	}
	
	@Override
	public void returnItem(UserItem item) {
		
		int itemId = item.getItemId();
		int currentAvailability = iMovieDAO.getAvailability(itemId);
		int udatedAvailability = currentAvailability + 1;
		iMovieDAO.updateAvailability(itemId, udatedAvailability);
	
	}

	@Override
	public void sendEmail(UserItem item) {
		
		String title = item.getTitle();
		String email = item.getEmail();

		emailDetails.setSubject("Reg : Movie titled " + title + " is available in the library!");
		emailDetails.setBody("Dear " + email + " ,<br/><br/>" + "This is to notify you that the Movie titled " + title
				+ " is booked for you!" + "<br/><br/>" + "Regards, " + "<br/>" + " Public Library.");
		emailDetails.setUserEmailID(email);
		try {
			EmailUtility.sendmail(emailDetails);
		} catch (MessagingException | IOException e) {

			logger.log(Level.ALL, "Check Email Sender class!", e);
		}
		
	}

	@Override
	public boolean isItemOnHold(int itemId) {
		boolean isItemOnHold = false;

		isItemOnHold = iUserItemDAO.isItemOnHold(itemId);
		if (isItemOnHold) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public UserItem getTheNextUserInLine(int itemId) {
		userOnHold = new UserItem();
		userOnHold = iUserItemDAO.getTheNextUserInLine(itemId);
		return userOnHold;
	}

	@Override
	public void removeUserFromHold(UserItem userOnHold) {
		
		iUserItemDAO.removeUserFromHold(userOnHold);

		
	}

}
