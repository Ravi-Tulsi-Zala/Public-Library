package com.library.itemDescription;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Display;

public class BookDescription implements IDescription{
	
	Book book;
	String cover;
	
	public BookDescription(Display display)
	{
		DAOFactory factory = new DAOFactory();
		IBookDAO bookDAO = factory.makeBookDAO();
		int itemID = display.getItemID();
		this.book = bookDAO.getBookByID(itemID);
		cover = display.getImage();
	}
	
	public String getCover() {
		return cover;
	}

	public String getDescription() 
	{
		return null;
	}
}
