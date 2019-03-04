package com.library.singIn;

import com.library.interfaces.IUserBasicInfo;

public class SignInController {

	IUserBasicInfo basic;
	User user;
//	SignUpMocked mockDataBase;

	
	public SignInController(IUserBasicInfo basic) {
		this.basic = basic;
//		clientValidation();
	}
	
}
