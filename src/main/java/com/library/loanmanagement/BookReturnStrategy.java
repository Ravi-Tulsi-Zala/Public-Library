package com.library.loanmanagement;

<<<<<<< HEAD
import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.businessModels.UserItem;
import com.library.dao.IBookDAO;
import com.library.dao.IUserItemDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
import com.library.email.EmailDetails;
import com.library.email.SendEmail;
import com.library.forgotPassword.ForgotPasswordController;
=======
import com.library.businessModels.UserItem;
>>>>>>> ec578c5e1375f0ffad7bf01e4611a7aa35a7a6bf

public class BookReturnStrategy implements IReturnItemStrategy {

	@Override
	public boolean returnItem(UserItem item) {
		
		return false;
	}

}
