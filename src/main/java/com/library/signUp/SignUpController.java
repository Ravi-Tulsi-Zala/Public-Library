package com.library.signUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IUserDAO;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.businessModels.Salt;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;
import com.library.businessModels.UserExtendedInfo;
import com.library.signIn.AuthenticateUserForms;
import com.library.signIn.AuthenticationAbstract;
import com.library.signIn.SignInController;

public class SignUpController implements ISignUpController {
	private List<Entry<String, String>> listofValidationErrors = null;
	private IUserBasicInfo userBasicInfo = null;
	private IUserExtendedInfo userExtendedInfo = null;
	private User user = null;
	private Salt salt = null;
	private static final Logger logger = LogManager.getLogger(SignUpController.class);

	public SignUpController(User user) {
		this.user = user;
		this.salt = new Salt();
	}

	public ArrayList<Entry<String, String>> authenticateSignUp() {
		try {
			
			userExtendedInfo = new UserExtendedInfo();
			userBasicInfo = new UserBasicInfo();
			addSaltToPassword();
			userBasicInfo.setEmail(user.getEmail());
			userBasicInfo.setPassword(salt.getSaltedPassword());
			userExtendedInfo.setCPassword(user.getCpassword());
			userExtendedInfo.setFullname(user.getFullName());
			userExtendedInfo.setPhone(user.getPhoneNumber());
			listofValidationErrors = AuthenticateUserForms.instance().signUpUserData(userBasicInfo, userExtendedInfo);
			if (listofValidationErrors.size() == 0) {
				boolean status = registerUser();
				if (status) {
					logger.log(Level.ALL, "User has successfully registered.");
				} else {
					logger.log(Level.ALL, "User has not registered.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	private void addSaltToPassword() {
		salt.setSaltedPassword(user.getPassword(), AuthenticationAbstract.saltValue);
	}

	private boolean registerUser() throws Exception {
		boolean isUserRegistered = false;
		try {
			IDAOFactory factory = new DAOFactory();
			IUserDAO userDAO = factory.makeUserDAO();
			isUserRegistered = userDAO.registerUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUserRegistered;
	}
}
