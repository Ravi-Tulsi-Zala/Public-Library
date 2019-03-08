package com.library.population;

import java.util.ArrayList;

public interface IMyUser {
	
	public IUserExtendedInfo getUserExtendedInfo();
	public IUserBasicInfo getUserBasicInfo();
	public ArrayList<IUserItem> getUserItems();

}
