package com.library.dao;
import java.util.List;
import com.library.businessModels.UserItem;

public interface IUserItemDAO {
	
	public List<UserItem> getAllBorrowedItems();
	public boolean removeItem(UserItem item);
	public boolean addItem(UserItem item,int itemID);	
	public boolean isItemOnHold(UserItem item);
	public boolean isItemBorrowed(UserItem item);
	public boolean addItemOnHold(UserItem item);
}
