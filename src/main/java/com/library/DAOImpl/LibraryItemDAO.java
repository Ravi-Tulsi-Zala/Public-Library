
package com.library.DAOImpl;
import java.util.ArrayList;
import java.util.List;
import com.library.BusinessModels.Book;
import com.library.DAO.IBookDAO;
import com.library.DAO.ILibraryItemDAO;

public class LibraryItemDAO implements ILibraryItemDAO {

	@Override
	public List<Integer> getRecentlyAdded() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getMostPopular() {
		// TODO Auto-generated method stub
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
