package com.library.signUp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonForm {

	@NotNull
	@Size(min = 2, max = 30)
	private String fullName;
	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@NotNull
	@Min(18)
	private Integer age;
	@NotNull
	@Size(max = 10)
	private String phoneNumber;
	@NotNull
	private String email;
	@NotNull
	@Size(min = 2, max = 30)
	// @Pattern(regexp="/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/")
	@Pattern(regexp = "[A-Z]")
	private String password;
	@NotNull
	@Size(min = 2, max = 30)
	// @Pattern(regexp="/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/")
	@Pattern(regexp = "[A-Z]")
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

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
