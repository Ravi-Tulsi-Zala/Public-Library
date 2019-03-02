package com.library.DAO;

import java.util.ArrayList;

import com.library.BusinessModels.Book;

public interface IBookDAO {
	public Book getBookByID(int itemID);
	public Book getBookByISBN(int bookISBN);
	public ArrayList<Book> getBookByTitle(String bookTitle);
	public ArrayList<Book> getBooksByAuthor(String bookAuthor);
	public ArrayList<Book> getBookByPublisher(String bookPublisher);
	public ArrayList<Book> getBookByDescription(String bookDescription);
	public String getBookTitle(int itemID);
	public Boolean deleteBookByID(int itemID);
	public Boolean createBook(Book book);
	public Boolean updateBook(Book book);
	public ArrayList<Book> getBookByKeyword(String keyword);
}
