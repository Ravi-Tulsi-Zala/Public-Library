package com.library.population;

import java.util.HashMap;

public class OnlineUsers implements IOnlineUsers {
	
	private HashMap<String, IMyUser> onlineUsers;
	
	public OnlineUsers() {
		onlineUsers = new HashMap<>();
	}

	@Override
	public void addUser(IMyUser user) {
		onlineUsers.put(user.getUserBasicInfo().getUserEmail(), user);
	}
	
	@Override
	public IMyUser getUser(String userEmail) {
		return onlineUsers.get(userEmail);
	}

}
