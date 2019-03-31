package com.library.borrowItem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.IDAO.IMovieDAO;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Display;

public class ItemStatus {
	
	
	private String userEmail;
	private int itemID;
	private String itemType;
	
	public ItemStatus(Display display,String userEmail) {
		this.userEmail = userEmail;
		this.itemID = display.getItemID();
		this.itemType = display.getItemType();
	}
	
	public Boolean isItemAvailable()
	{
		IDAOFactory factory = new DAOFactory();
		Boolean availability = false;
		if(itemType=="Book")
		{
			IBookDAO bookDAO = factory.makeBookDAO();
			availability = bookDAO.getAvailability(itemID);
		}
		else if(itemType=="Movie")
		{
			IMovieDAO movieDAO = factory.makeMovieDAO();
			availability = movieDAO.getAvailability(itemID);
		}
		else if(itemType=="Music")
		{
			IMusicDAO musicDAO = factory.makeMusicDAO();
			availability = musicDAO.getAvailability(itemID);
		}
		return availability;
	}
	
	public Boolean isItemAlreadyBooked()
	{
		return true;
	}
	
	public Boolean isItemOnHold()
	{
		return true;
	}
}
   