package com.library.population;

public class UserBasicInfo implements IUserBasicInfo {
	
	private String userEmail;
	private String userPassword;
	
	public UserBasicInfo(String userEmail, String userPassword) {
		super(); // should we call the Object class constructor??
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	@Override
	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public String getUserPassword() {
		return userPassword;
	}
}
