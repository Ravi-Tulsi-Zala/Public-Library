package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.signIn.SignInController;
import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.SignUpController;

public interface ILibraryFactory{
	public SignInController signIn(IUserBasicInfo userBasicInfo,HttpSession httpSession);
	public SignUpController signUp(IUserBasicInfo userBasicInfo,IUserExtendedInfo userExtendedInfo);
}