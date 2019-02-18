package com.library.buisness;

public interface IUserLogInController {
	
	public IUserPersistence.UserInfoStatus logInUser(IUserBasicInfo basicInfo);
}
