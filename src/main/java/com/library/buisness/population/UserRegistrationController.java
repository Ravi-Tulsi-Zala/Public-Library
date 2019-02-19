package com.library.buisness;

public class UserRegistrationController implements IUserRegistrationController {
	
	private IUserPersistence userPersistence;
	
	public UserRegistrationController(IUserPersistence userPersistence) {
		super();
		this.userPersistence = userPersistence;
	}

	@Override
	public IUserPersistence.UserInfoStatus registerNewUser(IUserInfo userInfo) {
		IMyUser user =  userPersistence.loadUser(userInfo.getUserBasicInfo());
		
		if(null != user) {
			return IUserPersistence.UserInfoStatus.USERNAME_ALREADY_EXISTS;
		}
		
		if(userPersistence.registerNewUser(userInfo.getUserExtendedInfo())) {
			return IUserPersistence.UserInfoStatus.SUCCESS;
		}
		return IUserPersistence.UserInfoStatus.DB_FAILURE;
	}
}
