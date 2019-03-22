package com.library.databaseTest;

import static org.junit.Assert.*;


import org.junit.Test;

import com.library.DAO.UserDAO;
import com.library.IDAO.IUserDAO;



public class UserTest {

	IUserDAO userDAO = new UserDAO();
	
	@Test
	public void getPasswordTest() {
		Boolean checkPassword =  userDAO.checkPassword("ravizala100@gmail.com", "trial3");
		assertEquals(true, checkPassword);
	}
	
	@Test
	public void changePasswordTest() {
		Boolean passwordChangeStatus = userDAO.changePassword("ravizala100@gmail.com", "trial3");
		assertEquals(true,passwordChangeStatus);
	}
/*	
	@Test
	public void registerUserTest()
	{
		 User user = new User();
		 user.setFullName("Nirav");
		 user.setEmailAddress("nr952727@dal.ca");
		 user.setPhoneNumber(909992915);
		 user.setPassword("yahoo");
		 Boolean checkUserRegisteration = userDAO.registerUser(user);
		 assertEquals(true,checkUserRegisteration);
	} */
	
	
}
