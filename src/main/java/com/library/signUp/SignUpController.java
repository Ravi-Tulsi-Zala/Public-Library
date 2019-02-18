package com.library.signUp;

import com.library.mockDB.MockDatabase;

public class SignUpController {
	User user;
	MockDatabase _obj;

	public SignUpController(User user) {
		this.user = user;
		_obj = new MockDatabase();
	}

	public boolean registerUser() {
		return _obj.register(user);
	}

	public boolean verifyCredentials() {
		return _obj.verify(user);
	}
	public User call() {
		return (User)_obj.getObject("dev");//(user);
	}

}
