package com.library.dbtest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.library.businessModels.UserItem;
import com.library.dao.IUserItemDAO;
import com.library.dao.UserItemDAO;

public class UserItemDAOTest {

	IUserItemDAO itemDao = new UserItemDAO();

	@Test
	public void addItemTest() {

		UserItem item = new UserItem();
		item.setCategory("Book");
		item.setEmail("nirav.solanki@dal.ca");
		item.setTitle("Alchemist");
		assertTrue(itemDao.addItem(item));
	}

}
