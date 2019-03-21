package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.businessModels.User;
import com.library.signIn.SignInController;
import com.library.signUp.SignUpController;

public interface ILibraryFactory{
	public ISignInController signIn(User user,HttpSession httpSession);
	public ISignUpController signUp(User user);
}