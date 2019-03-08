package com.library.signUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.signIn.AuthenticationFacade;

public class SignUpController {
	private List<Entry<String, String>> listofValidationErrors;
	private IUserBasicInfo userBasicInfo;
	private IUserExtendedInfo userExtendedInfo;
	
	public SignUpController(IUserBasicInfo userBasicInfo,IUserExtendedInfo userExtendedInfo) {
		this.userBasicInfo = userBasicInfo;
		this.userExtendedInfo = userExtendedInfo;
	}
	
	public ArrayList<Entry<String, String>> authenticateSignUp() {
		AuthenticationFacade facade = new AuthenticationFacade();
		listofValidationErrors = facade.signUpUserData(userBasicInfo,userExtendedInfo);
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
//			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;

	}

	private static void connectDB() {
		// TODO:DB calls

	}
}
