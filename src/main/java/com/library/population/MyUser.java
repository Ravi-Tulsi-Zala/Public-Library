package com.library.population;

import java.util.ArrayList;

public class MyUser implements IMyUser {
	
	private IUserInfo userInfo;
	private ArrayList<IUserItem> userItems;

	public MyUser(IUserInfo userInfo, ArrayList<IUserItem> userItems) {
		this.userInfo = userInfo;
		this.userItems = userItems;
	}
	
	@Override
	public IUserExtendedInfo getUserExtendedInfo() {
		return userInfo.getUserExtendedInfo();
	}

	@Override
	public IUserBasicInfo getUserBasicInfo() {
		return userInfo.getUserBasicInfo();
	}
	
	@Override
	public ArrayList<IUserItem> getUserItems() {
		return userItems;
	}

}
