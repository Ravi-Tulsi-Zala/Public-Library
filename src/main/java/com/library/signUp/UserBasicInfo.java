package com.library.signUp;

public class UserBasicInfo implements IUserBasicInfo{
	private String email;
	private String password;
	
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String getPwd() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
		
	}

	@Override
	public void setPwd(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

}
