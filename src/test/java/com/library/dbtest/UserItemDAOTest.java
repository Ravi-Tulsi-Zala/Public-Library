package com.library.dbtest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.library.businessModels.UserItem;
import com.library.dao.IUserItemDAO;
import com.library.dao.UserItemDAO;

public class UserItemDAOTest {

	IUserItemDAO itemDao = new UserItemDAO();
	
//
//	@Test
//	public void addItemTest() {
//
//		UserItem item = new UserItem();
//		item.setItemId(100009);
//		item.setCategory("Book");
//		item.setEmail("dv960112@dal.ca");
//		item.setTitle("The Republic");
//		assertTrue(itemDao.addItem(item));
//	}
	
	@Test
	public void addItemHoldTest()
	{
		UserItem item = new UserItem();
		item.setItemId(2001);
		item.setCategory("Movie");
		item.setEmail("eugene.shishlannikov@dal.ca");
		item.setTitle("Interstellar");
		assertTrue(itemDao.addItemOnHold(item));
	}

}
