package com.library.browsePage;

import java.util.List;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IBussinessModelSetter.IDisplaySetter;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Display;
import com.library.BussinessModelSetter.DisplaySetter;

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
