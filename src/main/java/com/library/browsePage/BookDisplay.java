package com.library.browsePage;

import java.util.ArrayList;
import java.util.List;

import com.library.DAO.BookDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Display;

public class BookDisplay implements IDisplayObjects{

	List<Display> displayBooks = new ArrayList<Display>();
	
	public BookDisplay()
	{
		List<Book> books = new ArrayList<Book>();
		BookDAO bookDAO = new BookDAO();
	}
	
	@Override
	public List<Display> makeDisplayItem(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
