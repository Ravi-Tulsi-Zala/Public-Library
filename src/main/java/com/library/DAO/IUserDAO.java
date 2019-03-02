package com.library.DAO;

import com.library.BusinessModels.User;

public interface IUserDAO {
	public Boolean checkPassword(String emailAddress,String Password);
	public Boolean changePassword(String emailAddress,String password);
	public Boolean registerUser(User user);
	public Boolean isUserActive(String emailAddress);
	public Boolean toggleStatus(String emailAddress);
}
