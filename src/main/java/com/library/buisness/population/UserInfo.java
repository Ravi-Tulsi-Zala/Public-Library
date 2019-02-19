package com.library.buisness.population;

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
	public void setUserBasicInfo(IUserBasicInfo userBasicInfo) {
		this.userBasicInfo = userBasicInfo;
	}

	@Override
	public IUserExtendedInfo getUserExtendedInfo() {
		return userExtendedInfo;
	}

	@Override
	public void setUserExtendedInfo(IUserExtendedInfo userExtendedInfo) {
		this.userExtendedInfo = userExtendedInfo;
	}

}
