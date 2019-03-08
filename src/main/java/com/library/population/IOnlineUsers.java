package com.library.population;

public interface IOnlineUsers {
	
	public void addUser(IMyUser user);

	public IMyUser getUser(String userEmail);

}
