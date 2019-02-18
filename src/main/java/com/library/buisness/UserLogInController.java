package com.library.buisness;

public class UserLogInController implements IUserLogInController {
	
	private IUserPersistence userPersistence;
	private IPasswordEncoder passwordEncoder;
	private IOnlineUsers onlineUsers;

	public UserLogInController(IUserPersistence userPersistence, 
			IPasswordEncoder passwordEncoder,
			IOnlineUsers onlineUsers) {
		super();
		this.userPersistence = userPersistence;
		this.passwordEncoder = passwordEncoder;
		this.onlineUsers = onlineUsers;
	}

	@Override
	public IUserPersistence.UserInfoStatus logInUser(IUserBasicInfo basicInfo) {
		
		if(null != onlineUsers.getUser(basicInfo.getUserEmail())) {
			return IUserPersistence.UserInfoStatus.USER_IS_ALREADY_LOGGED_IN;
		}
		
		IMyUser user =  userPersistence.loadUser(basicInfo);
		
		if(null == user) {
			return IUserPersistence.UserInfoStatus.WRONG_USER_NAME;
		}
		
		String encodedUserPassword = user.getUserBasicInfo().getUserPassword();
		if(encodedUserPassword.equals(passwordEncoder.encode(basicInfo.getUserPassword()))) {
			onlineUsers.addUser(user);
			return IUserPersistence.UserInfoStatus.SUCCESS;
		}
		
		return IUserPersistence.UserInfoStatus.WRONG_PASSWORD;
		
	}
}
