package com.library.DatabaseTest;

import static org.junit.Assert.*;


import org.junit.Test;

import com.library.DAO.IUserDAO;
import com.library.DAOImpl.UserDAOImpl;


public class UserTest {

	IUserDAO userDAO = new UserDAOImpl();
	@Test
	public void getPasswordTest() {
		String password =  userDAO.getPassword("ravizala@gmail.com");
		assertEquals("helloWorld",password);
	}

}
