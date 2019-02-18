package com.library.buisness;

public interface IUserRegistrationController {
	
	public IUserPersistence.UserInfoStatus registerNewUser(IUserInfo userInfo);
}
