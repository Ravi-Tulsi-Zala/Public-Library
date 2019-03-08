package com.library.buisness.population;

import com.library.model.IUsersDB;

public interface IUserLogInController {
	
	public IUsersDB.UserInfoStatus logInUser(IUserBasicInfo basicInfo);
}
