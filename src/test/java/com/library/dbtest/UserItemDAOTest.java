package com.library.dbtest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.library.DAO.UserItemDAO;
import com.library.IDAO.IUserItemDAO;
import com.library.businessModels.UserItem;

public class UserItemDAOTest {

	IUserItemDAO itemDao = new UserItemDAO();

	@Test
	public void addItemTest() {

		UserItem item = new UserItem();
		item.setCategory("Movie");
		item.setEmail("niravsolanki@dal.ca");
		item.setTitle("Avengers");
		assertTrue(itemDao.addItem(item));
	}

}
