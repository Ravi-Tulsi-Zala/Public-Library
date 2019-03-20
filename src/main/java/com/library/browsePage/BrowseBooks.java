package com.library.browsePage;

import java.util.List;

import com.library.BussinessModelSetter.DisplaySetter;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IBussinessModelSetter.IDisplaySetter;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Display;

public class BrowseBooks implements IBrowseDisplayObjects{

	IBookDAO bookDAO;
	IDisplaySetter displaySetter = new DisplaySetter();
	String itemType;
	
	public BrowseBooks()
	{
		IDAOFactory factory = new DAOFactory();
		bookDAO = factory.makeBookDAO();
		itemType = "Book";
	}
	
	@Override
	public List<Display> makeDisplayItem(String category) {
		List<Book> books = bookDAO.getBookByCategory(category);
		List<Display> displayBooks = displaySetter.getBookDisplayObjects(books);
		return displayBooks;
	}

	@Override
	public List<String> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getItemType() {
		return itemType;
	}

}
