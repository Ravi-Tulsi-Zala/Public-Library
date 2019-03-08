package com.library.buisness.population;

import com.library.model.IUsersDB;

public interface IUserRegistrationController {
	
	public IUsersDB.UserInfoStatus registerNewUser(IUserInfo userInfo);
}
