package com.library.buisness;

public class UserExtendedInfo implements IUserExtendedInfo {
	
	private String phoneNumber;
	private String fullName;

	public UserExtendedInfo(String phoneNumber, String fullName) {
		super();
		this.phoneNumber = phoneNumber;
		this.fullName = fullName;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
