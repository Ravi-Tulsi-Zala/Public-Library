package com.library.DAO;
import java.util.List;

import com.library.businessModels.UserItem;

public interface IUserItemDAO {
	
	public List<UserItem> getAllBorrowedItems();
	public boolean removeItem(UserItem item);
	public boolean addItem(UserItem item);	
	public boolean isItemOnHold(int itemId);
	public boolean isItemBorrowed(UserItem item);
	public boolean addItemOnHold(UserItem item);
	public UserItem getTheNextUserInLine(int itemId);
	public void removeUserFromHold(UserItem userOnHold);
}
