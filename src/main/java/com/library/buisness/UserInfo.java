package com.library.buisness;

public class UserInfo implements IUserInfo {
	
	private IUserBasicInfo userBasicInfo;
	private IUserExtendedInfo userExtendedInfo;
	
	public UserInfo(IUserBasicInfo userBasicInfo, IUserExtendedInfo userExtendedInfo) {
		
	}

	@Override
	public IUserBasicInfo getUserBasicInfo() {
		return userBasicInfo;
	}

	@Override
	public IUserExtendedInfo getUserExtendedInfo() {
		return userExtendedInfo;
	}

}
