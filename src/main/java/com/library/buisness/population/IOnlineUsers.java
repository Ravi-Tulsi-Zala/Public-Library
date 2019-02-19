package com.library.buisness.population;

public interface IOnlineUsers {
	
	public void addUser(IMyUser user);

	public IMyUser getUser(String userEmail);

}
