package com.library.IDAO;

import com.library.businessModels.User;

public interface IUserDAO {
	public String checkPassword(String emailAddress,String password);
	public Boolean changePassword(String emailAddress,String password);
	public Boolean registerUser(User user);
}
