package com.library.borrowItem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.IDAO.IMovieDAO;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Display;
import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.UserItem;

public class ItemStatus {
	
	static final String borowed= "Already Borrowed";
	static final String onHold = "Reserved Item";
	static final String available = "Borrow";
	static final String reserve = "Reserve";
	
	UserItem userItem;
	int itemID;
	
	public ItemStatus(DisplayDetailed displayDetailed,String userEmail) {
		userItem.setTitle(displayDetailed.getTitle());
		userItem.setCategory(displayDetailed.getItemType());
		userItem.setEmail(userEmail);
		itemID = displayDetailed.getItemID();
	}
	
	private Boolean isItemAvailable()
	{
		IDAOFactory factory = new DAOFactory();
		Boolean availability = false;
		if(userItem.getEmail().equals("Book"))
		{
			IBookDAO bookDAO = factory.makeBookDAO();
			availability = bookDAO.getAvailability(itemID);
		}
		else if(userItem.getEmail().equals("Movie"))
		{
			IMovieDAO movieDAO = factory.makeMovieDAO();
			availability = movieDAO.getAvailability(itemID);
		}
		else if(userItem.getEmail().equals("Music"))
		{
			IMusicDAO musicDAO = factory.makeMusicDAO();
			availability = musicDAO.getAvailability(itemID);
		}
		return availability;
	}
	
	private Boolean isItemAlreadyBooked()
	{
	  	return true;
	}
	
	private Boolean isItemAlreadyOnHold()
	{
		return true;
	}
	
	public String getItemStatus()
	{
		if(isItemAlreadyBooked())
		{
			return borowed;
		}
		else if(isItemAlreadyOnHold())
		{
			return onHold;
		}
		else if(isItemAvailable())
		{
			return available;
		}
	}
}
   