package com.library.signUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAO.IUserDAO;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.businessModels.Salt;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;
import com.library.businessModels.UserExtendedInfo;
import com.library.messages.Messages;
import com.library.signIn.AuthenticatedUsers;
import com.library.validatations.ValidateUserForms;
import com.library.validatations.ValidateUserFormsAbstract;
import com.library.welcomePage.UserSessionDetail;

public class SignUpController implements ISignUpController {
	private List<Entry<String, String>> listofValidationErrors = null;
	private IUserBasicInfo userBasicInfo = null;
	private IUserExtendedInfo userExtendedInfo = null;
	private User user = null;
	private Salt salt = null;
	private HttpSession httpSession = null;
	private static final Logger logger = LogManager.getLogger(SignUpController.class);

	public SignUpController(User user, HttpSession httpSession) throws Exception {
		this.user = user;
		this.salt = new Salt();
		ValidateUserForms.instance().setErrorStringToHTML();
		ValidateUserForms.instance().setValidationRules();
		this.httpSession = httpSession;
	}

	public ArrayList<Entry<String, String>> validateSignUp() throws Exception {
		userExtendedInfo = new UserExtendedInfo();
		userBasicInfo = new UserBasicInfo();
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPassword(user.getPassword());
		userExtendedInfo.setCPassword(user.getCpassword());
		userExtendedInfo.setFullname(user.getFullName());
		userExtendedInfo.setPhone(user.getPhoneNumber());
		listofValidationErrors = ValidateUserForms.instance().signUpUserData(userBasicInfo, userExtendedInfo);

		if (listofValidationErrors.size() == 0) {
			boolean status = registerUser();
			if (status) {
				AuthenticatedUsers authUsers = AuthenticatedUsers.instance();
				authUsers.addAuthenticatedUser(httpSession, userBasicInfo.getEmail());
				UserSessionDetail.setClientActiveStatus(Messages.Logout.getMessage());
				UserSessionDetail.setAvailableUserID(user.getEmail());
				logger.log(Level.ALL, "User has successfully registered.");
			} else {
				logger.log(Level.ALL, "User has not registered.");
			}
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	private void addSaltToPassword() {
		salt.setSaltedPassword(user.getPassword(), ValidateUserFormsAbstract.saltValue);
		user.setPassword(salt.getSaltedPassword());
	}

	private boolean registerUser() throws Exception {
		boolean isUserRegistered = false;
		IDAOFactory factory = new DAOFactory();
		IUserDAO userDAO = factory.makeUserDAO();
		addSaltToPassword();
		isUserRegistered = userDAO.registerUser(user);
		logger.log(Level.ALL, "registerUser method completed successfully.");
		return isUserRegistered;
	}
}
