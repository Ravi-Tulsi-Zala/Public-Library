package com.library.messages;

public enum Messages {
	
	RegisterLogin("Register / Login"),
	AdminEmailID("Administrator"),
	Logout("Logout"),
	WelcomePageRedirect("redirect:welcome"),
	SignInPageRedirect("redirect:signIn"),
	SignUpPageRedirect("redirect:signUp"),
	ForgotPassPageRedirect("redirect:forgotPassword"),
	ErrorPageRedirect("redirect:ErrorPage"),
	UnableToConnectToDB("Unable to connect to database"),
	GetLatestBookQuery("SELECT distinct * FROM books order by books.Item_ID desc limit "),
	GetLatestMovieQuery("SELECT distinct * FROM movie order by movie.Item_ID desc limit "),
	GetLatestMusicQuery("SELECT distinct * FROM music order by music.Item_ID desc limit "),
	GetFavBookQuery("SELECT distinct * FROM books order by Count desc limit "),
	GetFavMovieQuery("SELECT distinct * FROM movie order by Count desc limit "),
	GetFavMusicQuery("SELECT distinct * FROM music order by Count desc limit "),
	SignInForm("SignInForm"),
	SignUpForm("SignUpForm"),
	Welcome("Welcome"),
	ForgotPassword("ForgotPassword"),
	ErrorPage("ErrorPage"),
	SignUpErrorStatement("Something went wrong while registering the User, please check detailed logs."),
	SignInErrorStatement("Something went wrong while signing in the User, please check detailed logs."),
	WelcomeSQlErrorStatement("Some problem occured while connection with Database in welcome controller."),
	WelcomeErrorStatement("Something went wrong in Welcome controller please look into logs."),
	ForgotPwdErrorStatement("Some generic error occured while in forgotPassword controller, look for logs."),
	ForgotPwdEmailErrorStatement("Some problem occured while sending a email."),
	ParsingErrorStatement("Some problem occured while parsing XML."),
	EmailExistErrorStatement("Email already exists. Please register with different email"),
	validationSignUp("signUpUserData method implemented completely"),
	validationSignIn("signInUserData method implemented completely"),
	BrowsePageCategory("BrowsePageCategory"),
	BrowsePageItems("BrowsePageItems"),
	ItemDetail("itemDetail"),
	ItemDetailRedirect("redirect:/itemDetail/");
	
	
	
	
	
	
	private String message;
	
	Messages(String message) {
		
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
	
}
