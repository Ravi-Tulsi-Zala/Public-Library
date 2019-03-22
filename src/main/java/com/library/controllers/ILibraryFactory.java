package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.businessModels.User;
import com.library.signIn.ISignInController;
import com.library.signUp.ISignUpController;

public interface ILibraryFactory{
	public ISignInController signIn(User user,HttpSession httpSession);
	public ISignUpController signUp(User user);
	public IForgotPasswordController forgotPassword(RecoverPassword recoverDetails);
}