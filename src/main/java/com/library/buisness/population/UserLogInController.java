package com.library.buisness.population;

import com.library.model.IUsersDB;

public class UserLogInController implements IUserLogInController {
	
	private IUsersDB usersDB;
	private IPasswordEncoder passwordEncoder;
	private IOnlineUsers onlineUsers;

	public UserLogInController(IUsersDB userPersistence, 
			IPasswordEncoder passwordEncoder,
			IOnlineUsers onlineUsers) {
		super();
		this.usersDB = userPersistence;
		this.passwordEncoder = passwordEncoder;
		this.onlineUsers = onlineUsers;
	}

	@Override
	public IUsersDB.UserInfoStatus logInUser(IUserBasicInfo logInBasicInfo) {
		
		if(null != onlineUsers.getUser(logInBasicInfo.getUserEmail())) {
			return IUsersDB.UserInfoStatus.USER_IS_ALREADY_LOGGED_IN;
		}
		
		IMyUser user =  usersDB.loadUser(logInBasicInfo);
		
		if(null == user) {
			return IUsersDB.UserInfoStatus.WRONG_USER_NAME;
		}
		
		String encodedUserPassword = user.getUserBasicInfo().getUserPassword();
		if(encodedUserPassword.equals(passwordEncoder.encode(logInBasicInfo.getUserPassword()))) {
			onlineUsers.addUser(user);
			return IUsersDB.UserInfoStatus.SUCCESS;
		}
		
		return IUsersDB.UserInfoStatus.WRONG_PASSWORD;
		
	}
}
