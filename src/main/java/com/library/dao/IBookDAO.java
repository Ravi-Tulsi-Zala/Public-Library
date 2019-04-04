package com.library.DAO;

import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;
import com.library.search.BookSearch;

public interface IBookDAO {
	public Book getBookByID(int itemID);
	public Boolean deleteBookByID(int itemID);
	public int createBook(Book book);
	public Boolean updateBook(Book book);
	public List<LibraryItem> getBooksBySearchTerms(BookSearch requestDetails, String searchTerms);
	public List<Book> getBookByCategory(String category);
	public List<String> getBookCategories();
	public int getAvailability(int itemID);
	public boolean checkBookDuplicacy(Book book);
	public void updateAvailability(int itemId, int updatedAvailability);
	public Boolean increaseCount(int itemID);
}

