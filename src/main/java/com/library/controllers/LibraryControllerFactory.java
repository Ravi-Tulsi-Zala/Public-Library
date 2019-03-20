package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.signIn.SignInController;
import com.library.signIn.User;
import com.library.signUp.SignUpController;

public class LibraryControllerFactory implements ILibraryFactory {

	@Override
	public SignInController signIn(User user, HttpSession httpSession) {
		return new SignInController(user, httpSession);
	}

	@Override
	public SignUpController signUp(User user) {
		return new SignUpController(user);
	}

}
