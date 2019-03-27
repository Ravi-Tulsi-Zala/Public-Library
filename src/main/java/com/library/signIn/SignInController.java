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
import com.library.welcomePage.AdminPage;

public class SignInController implements ISignInController {

	private IUserBasicInfo userBasicInfo = null;
	private User user = null;
	private Salt salt = null;
	private HttpSession httpSession = null;
	private List<Entry<String, String>> listofValidationErrors = null;
	private static final Logger logger = LogManager.getLogger(SignInController.class);

	public SignInController(User user, HttpSession httpSession) {
		this.user = user;
		salt = new Salt();
		userBasicInfo = new UserBasicInfo();
		this.httpSession = httpSession;
	}

	public String checkUserCredential() {
		DAOFactory factory = new DAOFactory();
		IUserDAO userDAO = factory.makeUserDAO();
		addSaltToPassword();
		if (userDAO.checkPassword(user.getEmail(), salt.getSaltedPassword())) {
			logger.log(Level.ALL, "User has successfully logged in.");
			AdminPage.setAdminAvailable(false);
			return "Welcome";

		} else if (userBasicInfo.getEmail().equals(AuthenticationAbstract.isAdmin)
				&& userBasicInfo.getPassword().equals(AuthenticationAbstract.isAdminPwd)) {
			AdminPage.setAdminAvailable(true);
			return "Welcome";
		}
		return "SignInForm";
	}

	private void addSaltToPassword() {
		salt.setSaltedPassword(user.getPassword(), AuthenticationAbstract.saltValue);
	}

	public ArrayList<Entry<String, String>> authenticateSignIn() {
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPassword(user.getPassword());
		listofValidationErrors = AuthenticateUserForms.instance().signInUserData(userBasicInfo);
		if (listofValidationErrors.size() == 0) {
			AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, userBasicInfo.getEmail());

		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}
}
