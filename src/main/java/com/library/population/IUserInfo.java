package com.library.population;

public interface IUserInfo {
	
	public IUserBasicInfo getUserBasicInfo();
	public void setUserBasicInfo(IUserBasicInfo userBasicInfo);
	public IUserExtendedInfo getUserExtendedInfo();
	public void setUserExtendedInfo(IUserExtendedInfo userExtendedInfo);
}
