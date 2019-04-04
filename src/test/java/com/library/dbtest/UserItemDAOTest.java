package com.library.dbtest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.library.DAO.UserItemDAO;
import com.library.IDAO.IBookDAO;
import com.library.IDAO.IUserItemDAO;
import com.library.businessModels.Book;
import com.library.businessModels.UserItem;

public class UserItemDAOTest {

	IUserItemDAO itemDao = new UserItemDAO();
	
//
//	@Test
//	public void addItemTest() {
//
//		UserItem item = new UserItem();
//		item.setItemId(100003);
//		item.setCategory("Book");
//		item.setEmail("nirav.solanki@dal.ca");
//		item.setTitle("Alchemist");
//		assertTrue(itemDao.addItem(item));
//	}
	
	@Test
	public void addItemHoldTest()
	{
		UserItem item = new UserItem();
		item.setItemId(2004);
		item.setCategory("Movie");
		item.setEmail("ravizala.emp@gmail.com");
		item.setTitle("Inception");
		assertTrue(itemDao.addItemOnHold(item));
	}

}
