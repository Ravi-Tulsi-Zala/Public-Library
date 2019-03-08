package com.library.DAO;

import java.util.ArrayList;
import java.util.List;
import com.library.BusinessModels.Book;
import com.library.IDAO.IBookDAO;

public class LibraryItemDAO implements com.library.IDAO.ILibraryItemDAO {

	@Override
	public List<Integer> getRecentlyAdded() {
	
		return null;
	}

	@Override
	public List<Integer> getMostPopular() {
	
		return null;
	}
	
	@Override
	public List<Integer> getItemFromKeyword(String keyword) {
		IBookDAO bookDAO = new BookDAO();
		List<Integer> items = new ArrayList<Integer>();
		List<Book> bookItem = new ArrayList<Book>();
		bookItem = bookDAO.getBookByKeyword(keyword);
		int itemID;
		for(int i=0;i<bookItem.size();i++)
		{
			itemID = bookItem.get(i).getItemID();
			items.add(i,itemID);
		}
		return items;
	}
}
