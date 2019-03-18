package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.signIn.SignInController;
import com.library.signUp.SignUpController;

public class LibraryControllerFactory implements ILibraryFactory{

	@Override
	public SignInController signIn(IUserBasicInfo userBasicInfo, HttpSession httpSession) {
		return new SignInController(userBasicInfo,httpSession);
	}

	@Override
	public SignUpController signUp(IUserBasicInfo userBasicInfo, IUserExtendedInfo userExtendedInfo) {
		return new SignUpController(userBasicInfo,userExtendedInfo);
	}
	
}
