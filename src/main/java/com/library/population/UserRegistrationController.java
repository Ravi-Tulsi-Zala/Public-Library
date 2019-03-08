package com.library.population;

import com.library.model.IUsersDB;

public class UserRegistrationController implements IUserRegistrationController {
	
	private IUsersDB usersDB;
	
	public UserRegistrationController(IUsersDB usersDB) {
		this.usersDB = usersDB;
	}

	@Override
	public IUsersDB.UserInfoStatus registerNewUser(IUserInfo userInfo) {
		IMyUser user =  usersDB.loadUser(userInfo.getUserBasicInfo());
		
		if(null != user) {
			return IUsersDB.UserInfoStatus.USERNAME_ALREADY_EXISTS;
		}
		
		if(usersDB.registerNewUser(userInfo)) {
			return IUsersDB.UserInfoStatus.SUCCESS;
		}
		return IUsersDB.UserInfoStatus.DB_FAILURE;
	}
}
