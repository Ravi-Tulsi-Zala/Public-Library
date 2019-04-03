package com.library.signIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IUserDAO;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.Salt;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;
import com.library.messages.Messages;
import com.library.validatations.ValidateUserForms;
import com.library.validatations.ValidateUserFormsAbstract;
import com.library.welcomePage.AdminPage;

public class SignInController implements ISignInController {

	private IUserBasicInfo userBasicInfo = null;
	private User user = null;
	private Salt salt = null;
	private HttpSession httpSession = null;
	private List<Entry<String, String>> listofValidationErrors = null;
	private static final Logger logger = LogManager.getLogger(SignInController.class);

	public SignInController(User user, HttpSession httpSession) throws Exception {
		this.user = user;
		this.salt = new Salt();
		this.userBasicInfo = new UserBasicInfo();
		this.httpSession = httpSession;
		ValidateUserForms.instance().setErrorStringToHTML();
		ValidateUserForms.instance().setValidationRules();
	}

	public String checkUserCredential() throws Exception {
		String redirectToWelcome = Messages.WelcomePageRedirect.getMessage();
		DAOFactory factory = new DAOFactory();
		IUserDAO userDAO = factory.makeUserDAO();
		addSaltToPassword();
		AuthenticatedUsers authUsers = AuthenticatedUsers.instance();
		authUsers.addAuthenticatedUser(httpSession, userBasicInfo.getEmail());
		if (userDAO.checkPassword(user.getEmail(), salt.getSaltedPassword())) {
			logger.log(Level.ALL, "User has successfully logged in.");
			AdminPage.setAvailableAdmin(false);
			AdminPage.setAvailableUserID(authUsers.getUserEmail(httpSession));
			AdminPage.setClientActiveStatus(Messages.Logout.getMessage());
			return redirectToWelcome;

		} else if (userBasicInfo.getEmail().equals(ValidateUserFormsAbstract.isAdmin)
				&& userBasicInfo.getPassword().equals(ValidateUserFormsAbstract.isAdminPwd)) {
			AdminPage.setAvailableAdmin(true);
			AdminPage.setAvailableUserID(Messages.AdminEmailID.getMessage());
			AdminPage.setClientActiveStatus(Messages.Logout.getMessage());
			return redirectToWelcome;
		}
		logger.log(Level.ALL, "checkUserCredential method implemented successfully.");
		AdminPage.setClientActiveStatus(Messages.Logout.getMessage());
		return redirectToWelcome;
	}

	private void addSaltToPassword() {
		salt.setSaltedPassword(user.getPassword(), ValidateUserFormsAbstract.saltValue);
	}

	public ArrayList<Entry<String, String>> validateSignIn() throws Exception {
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPassword(user.getPassword());
		listofValidationErrors = ValidateUserForms.instance().signInUserData(userBasicInfo);
		logger.log(Level.ALL, "validateSignIn method implemented successfully.");
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}
}
