package com.library.borrowItem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IUserItemDAO;
import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.UserItem;

public class BookItem {
	
	static final String available = "Borrow";
	static final String reserve = "Reserve";
	private UserItem userItem;
	private IUserItemDAO userItemDAO;
	private String itemType;
	private int itemID;
	
	public BookItem(DisplayDetailed displayDetailed,String userEmail)
	{
		userItem.setTitle(displayDetailed.getTitle());
		userItem.setCategory(displayDetailed.getItemType());
		userItem.setEmail(userEmail);
		itemType = displayDetailed.getItemType();
		itemID = displayDetailed.getItemID();
		IDAOFactory factory = new DAOFactory();
		userItemDAO = factory.makeUserItemDAO();
	}
	
	private Boolean borrowBook()
	{
		return userItemDAO.addItem(userItem);
	}
	
	private Boolean holdItem()
	{
		return userItemDAO.addItemOnHold(userItem);
	}
	
	public Boolean bookItem(String status)
	{
		Boolean isItemBooked = false;
		if(status.equals(available))
		{
			isItemBooked = borrowBook();
		}
		else if(status.equals(reserve))
		{
			isItemBooked = holdItem();
		}
		if(isItemBooked)
		{
			ChangeItemCount countChanger = new ChangeItemCount(itemType, itemID);
			countChanger.changeCount();
		}
		
		return isItemBooked;
	}
}
