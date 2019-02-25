package com.library.signUp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	@NotNull
	@Size(max = 30)
	private String fullName;
	@NotNull
	@Size(min = 10,max = 15) //if country code is also appended.
	private String phoneNumber;
	@NotNull
	@Pattern( regexp = "^(.+)@(.+)$")
	private String email;
	@NotNull
	@Size(min = 8,max=10)
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	private String password;
	@NotNull
	@Size(min = 8,max=10)
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	private String cpassword;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullNam) {
		this.fullName = fullNam;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

   
}
