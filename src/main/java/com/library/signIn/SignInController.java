package com.library.signIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.library.interfaces.IUserBasicInfo;

public class SignInController {

	private IUserBasicInfo userBasicInfo;
	private HttpSession httpSession;
	private List<Entry<String, String>> listofValidationErrors;

	public SignInController(IUserBasicInfo userBasicInfo, HttpSession httpSession) {
		this.userBasicInfo = userBasicInfo;
		this.httpSession = httpSession;
	}

	public boolean connectDB() {

		// TODO: call DB
		return true;
	}

	public ArrayList<Entry<String, String>> authenticateSignIn() {
		AuthenticationFacade facade = new AuthenticationFacade();
		listofValidationErrors = facade.signInUserData(userBasicInfo);
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
			AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, userBasicInfo.getEmail());
//			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;

	}
}
