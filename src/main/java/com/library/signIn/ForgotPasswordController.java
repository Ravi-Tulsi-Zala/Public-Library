package com.library.signIn;

public class ForgotPasswordController {

	public void fun() {
		ForgotPassword forgotPassword =new ForgotPassword();
		forgotPassword.setEmail("dv960112@dal.ca");
		forgotPassword.setBody("dv960112@dal.ca");
		forgotPassword.setSubject("Password retrieval");

		forgotPassword.sendEmailToUser();
	}
}
