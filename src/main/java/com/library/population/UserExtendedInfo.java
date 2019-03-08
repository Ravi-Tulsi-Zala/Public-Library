package com.library.population;

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
	public String getFullName() {
		return fullName;
	}
}
