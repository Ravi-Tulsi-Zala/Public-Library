package com.library.DAO;

import java.util.ArrayList;

import com.library.POJO.Book;

public interface IBookDAO {
	public Book getBookByID(int itemID);
	public ArrayList<Book> getBookByTitle(String bookTitle);
	public ArrayList<Book> getBooksByAuthor(String bookAuthor);
	public ArrayList<Book> getBookByPublisher(String bookPublisher);
	public ArrayList<Book> getBookByDescription(String bookDescription);
	public Book getBookByISBN(int bookISBN);
	public void deleteBookByID(int itemID);
	public void createBook(Book book);
	public void updateBook(Book book);
}
