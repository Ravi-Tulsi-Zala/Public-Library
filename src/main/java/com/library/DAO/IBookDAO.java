package com.library.DAO;

import java.util.List;

import com.library.BusinessModels.Book;

public interface IBookDAO {
	public Book getBookByID(int itemID);
	public Book getBookByISBN(int bookISBN);
	public List<Book> getBookByTitle(String bookTitle);
	public List<Book> getBooksByAuthor(String bookAuthor);
	public List<Book> getBookByPublisher(String bookPublisher);
	public List<Book> getBookByDescription(String bookDescription);
	public String getBookTitle(int itemID);
	public Boolean deleteBookByID(int itemID);
	public Boolean createBook(Book book);
	public Boolean updateBook(Book book);
	public List<Book> getBookByKeyword(String keyword);
	public int getLastID();
	public void changeBooksItemID(int itemID);
}
