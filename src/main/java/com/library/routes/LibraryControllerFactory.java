package com.library.routes;

import javax.servlet.http.HttpSession;

import com.library.ForgotPassword.ForgotPasswordController;
import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.businessModels.User;
import com.library.signIn.ISignInController;
import com.library.signIn.SignInController;
import com.library.signUp.ISignUpController;
import com.library.signUp.SignUpController;
import com.library.welcomePage.IWelcomeController;
import com.library.welcomePage.WelcomePageController;

public class LibraryControllerFactory implements ILibraryFactory {

	@Override
	public ISignInController signIn(User user, HttpSession httpSession) {
		return new SignInController(user, httpSession);
	}

	@Override
	public ISignUpController signUp(User user) {
		return new SignUpController(user);
	}

	@Override
	public IForgotPasswordController forgotPassword(RecoverPassword recoverDetails) {
		return new ForgotPasswordController(recoverDetails);
	}

	@Override
	public IWelcomeController welcomePage() {
		return new WelcomePageController();
	}

}
