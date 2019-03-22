package com.library.IDAO;


import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Book;
import com.library.search.IBookSearchRequestDetails;

public interface IBookDAO {
	public Book getBookByID(int itemID);
	public Boolean deleteBookByID(int itemID);
	public Boolean createBook(Book book);
	public Boolean updateBook(Book book);
	LinkedList<Book> getBooksBySearchTerms(IBookSearchRequestDetails searchRequestDetails);
	public List<Book> getBookByCategory(String category);
}
