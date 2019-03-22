package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.businessModels.User;
import com.library.signIn.SignInController;
import com.library.signUp.SignUpController;

public class LibraryControllerFactory implements ILibraryFactory {

	@Override
	public ISignInController signIn(User user, HttpSession httpSession) {
		return new SignInController(user, httpSession);
	}

	@Override
	public ISignUpController signUp(User user) {
		return new SignUpController(user);
	}

}
