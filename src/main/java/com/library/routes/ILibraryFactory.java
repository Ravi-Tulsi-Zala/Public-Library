package com.library.routes;

import javax.servlet.http.HttpSession;

import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.additem.IAddBookController;
import com.library.additem.IAddMovieController;
import com.library.additem.IAddMusicController;
import com.library.additem.IItemCoverSetter;
import com.library.businessModels.User;
import com.library.signIn.ISignInController;
import com.library.signUp.ISignUpController;
import com.library.welcomePage.IWelcomeController;

public interface ILibraryFactory{
	public ISignInController signIn(User user,HttpSession httpSession) throws Exception;
	public ISignUpController signUp(User user);
	public IForgotPasswordController forgotPassword(RecoverPassword recoverDetails);
	public IAddBookController makeAddBookController();
	public IAddMovieController makeAddMovieController();
	public IAddMusicController makeAddMusicController();
	public IItemCoverSetter makeItemCoverSetter();
	public IWelcomeController welcomePage();

}