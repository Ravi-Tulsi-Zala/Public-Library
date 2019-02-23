package com.library.DAO;

import com.library.POJO.User;

public interface IUserDAO {
	public String getPassword(String emailAddress);
	public void changePassword(String emailAddress,String password);
	public void registerUser(User user);
}
