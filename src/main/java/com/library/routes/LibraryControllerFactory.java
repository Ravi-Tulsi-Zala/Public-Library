package com.library.routes;

import javax.servlet.http.HttpSession;

import com.library.ForgotPassword.ForgotPasswordController;
import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.additem.AddBookController;
import com.library.additem.AddMovieController;
import com.library.additem.AddMusicController;
import com.library.additem.IAddBookController;
import com.library.additem.IAddMovieController;
import com.library.additem.IAddMusicController;
import com.library.additem.IItemCoverSetter;
import com.library.additem.ItemCoverSetter;
import com.library.businessModels.User;
import com.library.signIn.ISignInController;
import com.library.signIn.SignInController;
import com.library.signUp.ISignUpController;
import com.library.signUp.SignUpController;
import com.library.welcomePage.IWelcomeController;
import com.library.welcomePage.WelcomePageController;

public class LibraryControllerFactory implements ILibraryFactory {

	@Override
	public ISignInController signIn(User user, HttpSession httpSession) throws Exception {
		return new SignInController(user, httpSession);
	}

	@Override
	public ISignUpController signUp(User user) throws Exception {
		return new SignUpController(user);
	}

	@Override
	public IForgotPasswordController forgotPassword(RecoverPassword recoverDetails) {
		return new ForgotPasswordController(recoverDetails);
	}

	@Override
	public IAddBookController makeAddBookController() {

		return new AddBookController();
	}

	@Override
	public IAddMovieController makeAddMovieController() {

		return new AddMovieController();
	}

	@Override
	public IAddMusicController makeAddMusicController() {

		return new AddMusicController();
	}

	@Override
	public IItemCoverSetter makeItemCoverSetter() {
		
		return new ItemCoverSetter();
		
	}

	public IWelcomeController welcomePage() {
		return new WelcomePageController();
	}

}
