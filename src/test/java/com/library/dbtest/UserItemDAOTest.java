package com.library.dbtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.library.businessModels.UserItem;
import com.library.dao.IUserItemDAO;
import com.library.dao.UserItemDAO;

public class UserItemDAOTest {

	IUserItemDAO itemDao = new UserItemDAO();

	@Test
	public void isBorrowed() {

		UserItemDAO userItemDAO = new UserItemDAO();
		UserItem userItem = new UserItem();
		userItem.setTitle("Myth of sysphus");
		userItem.setEmail("dv960112@dal.ca");
		Boolean isBoorwed = userItemDAO.isItemBorrowed(userItem);
		assertEquals(true, isBoorwed);
	}

}
