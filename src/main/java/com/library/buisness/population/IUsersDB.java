package com.library.buisness.population;

public interface IUsersDB {
	
	public enum UserInfoStatus {
		//should move to the DB
		WRONG_USER_NAME,
		WRONG_PASSWORD,
		USERNAME_ALREADY_EXISTS,
		USER_IS_ALREADY_LOGGED_IN,
		SUCCESS,
		DB_FAILURE
	}
	
	public boolean registerNewUser(IUserInfo userInfo);
	public IMyUser loadUser(IUserBasicInfo basicInfo);
}
