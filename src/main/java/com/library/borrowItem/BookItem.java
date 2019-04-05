package com.library.borrowItem;

import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.UserItem;
import com.library.dao.IUserItemDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;

public class BookItem {
	
	static final String available = "Borrow";
	static final String reserve = "Reserve";
	private UserItem userItem;
	private IUserItemDAO userItemDAO;
	
	public BookItem(DisplayDetailed displayDetailed,String userEmail)
	{
		userItem = new UserItem();
		userItem.setTitle(displayDetailed.getTitle());
		userItem.setCategory(displayDetailed.getItemType());
		userItem.setEmail(userEmail);
		userItem.setItemId(displayDetailed.getItemID());
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
			BookingEmailSender bookingEmailSender = new BookingEmailSender();
			bookingEmailSender.sendEmail(userItem);
			DescreaseAvailability decreaser = new DescreaseAvailability(userItem.getCategory());
			decreaser.decreaseAvailability(userItem.getItemId());
		}
		else if(status.equals(reserve))
		{
			isItemBooked = holdItem();
		}
		if(isItemBooked)
		{
			ChangeItemCount countChanger = new ChangeItemCount(userItem.getCategory(), userItem.getItemId());
			countChanger.changeCount();	
		}
		
		return isItemBooked;
	}
}
