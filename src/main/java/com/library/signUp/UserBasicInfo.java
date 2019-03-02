package com.library.signUp;

public class UserBasicInfo implements IUserBasicInfo{
	private String email;
	private String password;
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPwd() {
		return password;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public void setPwd(String password) {
		this.password = password;
	}

}
