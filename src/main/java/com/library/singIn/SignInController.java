package com.library.singIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.library.interfaces.IUserBasicInfo;

public class SignInController {

	private IUserBasicInfo basic;
	private List<Entry<String, String>> listofValidationErrors;

	public SignInController(IUserBasicInfo userBasicInfo) {
		this.basic = userBasicInfo;
	}

	public boolean connectDB() {

		// TODO: call DB
		return true;
	}

	public ArrayList<Entry<String, String>> authenticateSignIn() {
		AuthenticationFacade facade = new AuthenticationFacade();
		listofValidationErrors = facade.signInUserData(basic);
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
//			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;

	}
}
