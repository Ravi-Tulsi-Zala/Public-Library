package com.library.signUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IUserDAO;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;
import com.library.businessModels.UserExtendedInfo;
import com.library.controllers.ISignUpController;
import com.library.signIn.AuthenticateUserForms;

public class SignUpController implements ISignUpController {
	private List<Entry<String, String>> listofValidationErrors;
	private IUserBasicInfo userBasicInfo;
	private IUserExtendedInfo userExtendedInfo;
	private User user;

	public SignUpController(User user) {
		this.user = user;
	}

	public ArrayList<Entry<String, String>> authenticateSignUp() {
		try {
			userExtendedInfo = new UserExtendedInfo();
			userBasicInfo = new UserBasicInfo();
			userBasicInfo.setEmail(user.getEmail());
			userBasicInfo.setPwd(user.getPassword());
			userExtendedInfo.setCPassword(user.getCpassword());
			userExtendedInfo.setFullname(user.getFullName());
			userExtendedInfo.setPhone(user.getPhoneNumber());
			listofValidationErrors = AuthenticateUserForms.instance().signUpUserData(userBasicInfo, userExtendedInfo);
			if (listofValidationErrors.size() == 0) {
				registerUser();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
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
