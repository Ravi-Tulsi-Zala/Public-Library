package com.library.signIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IUserDAO;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;
import com.library.controllers.ISignInController;

public class SignInController implements ISignInController {

	private IUserBasicInfo userBasicInfo;
	private User user;
	private HttpSession httpSession;
	private List<Entry<String, String>> listofValidationErrors;

	public SignInController(User user, HttpSession httpSession) {
		this.user = user;
		userBasicInfo = new UserBasicInfo();
		this.httpSession = httpSession;
	}

	public String checkUserCredential() {
		DAOFactory factory = new DAOFactory();
		IUserDAO userDAO = factory.makeUserDAO();
		if (userDAO.checkPassword(user.getEmail(), user.getPassword())) {
			return "MainPage";
		} else if (userBasicInfo.getEmail().equals(Authentication.isAdmin)
				&& userBasicInfo.getPwd().equals(Authentication.isAdminPwd)) {
			return "AdminMainPage";
		}
		return "SignInForm";
	}

	public ArrayList<Entry<String, String>> authenticateSignIn() {

		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPwd(user.getPassword());
		listofValidationErrors = AuthenticateUserForms.instance().signInUserData(userBasicInfo);
		if (listofValidationErrors.size() == 0) {
			AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, userBasicInfo.getEmail());
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	public String isAdmin() {
		if (userBasicInfo.getEmail().equals(Authentication.isAdmin)) {
			return "AdminMainPage";
		}
		return "MainPage";
	}

}
