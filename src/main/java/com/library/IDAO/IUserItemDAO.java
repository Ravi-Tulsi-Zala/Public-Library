package com.library.IDAO;
import java.util.List;
import com.library.businessModels.UserItem;

public interface IUserItemDAO {
	
	public List<UserItem> getAllBorrowedItems();
	public boolean removeItem();
	public boolean addItem(UserItem item);	
}
