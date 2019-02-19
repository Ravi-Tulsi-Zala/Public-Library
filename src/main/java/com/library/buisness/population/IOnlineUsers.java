package com.library.buisness;

public interface IOnlineUsers {
	
	public void addUser(IMyUser user);

	public IMyUser getUser(String userEmail);

}
