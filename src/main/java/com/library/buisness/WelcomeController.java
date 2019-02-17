package com.library.buisness;

import com.library.mockDB.MockDatabase;

public class WelcomeController {

	User user;
	public WelcomeController(User user) {
	this.user = user;
	}
	public boolean verifyCredentials() {
		MockDatabase _obj = new MockDatabase();
		int val = 0;
		return _obj.verify(user);

	}
}
