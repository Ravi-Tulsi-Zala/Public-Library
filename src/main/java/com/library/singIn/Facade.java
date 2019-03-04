package com.library.singIn;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

public class Facade {
	
	private SignInController signInController;
	private SignUpController signUpController;
//	private IUserExtendedInfo userExtendedInfo;// = new UserExtendedInfo();
//	private IUserBasicInfo userBasicInfo; //= new UserBasicInfo();
	
	public Facade() {
		signInController = new SignInController();
		signUpController =  new SignUpController();
//		userExtendedInfo = new UserExtendedInfo();
//		userBasicInfo = new UserBasicInfo();
	}
	
	public SignUpController getSignUpObj() {
		
		return signUpController;
	}
	public SignInController getSignInObj() {
		
		return signInController;
	}
	
	public ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,IUserExtendedInfo userExtendedInfo){
			return signUpController.insertInDBIfAuthenticate(userBasicInfo, userExtendedInfo);
	}
	public ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo){
		return signInController.signInUserAfterAuthenticate(userBasicInfo);
}
	
//	public boolean isSignIn(IUserBasicInfo basic) {
//		return signInController.connectDB(basic);
//	}
	
	
}
