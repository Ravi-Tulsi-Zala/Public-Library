package com.library.browsePage;

import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Display;
import com.library.bussinessModelSetter.DisplaySetter;
import com.library.bussinessModelSetter.IDisplaySetter;
import com.library.dao.IBookDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;

public class BrowseBooks implements IBrowseDisplayObjects{

	private IBookDAO bookDAO;
	private String itemType;
	
	public BrowseBooks()
	{
		IDAOFactory factory = new DAOFactory();
		bookDAO = factory.makeBookDAO();
		itemType = "Book";
	}
	
	@Override
	public List<Display> itemsByCategory(String category) {
		IDisplaySetter displaySetter = new DisplaySetter();
		List<Book> books = bookDAO.getBookByCategory(category);
		List<Display> displayBooks = displaySetter.getBookDisplayObjects(books);
		return displayBooks;
	}

	@Override
	public List<String> getCategories() {
		List<String> categories = bookDAO.getBookCategories();
		return categories;
	}

	@Override
	public String getItemType() {
		return itemType;
	}

}
