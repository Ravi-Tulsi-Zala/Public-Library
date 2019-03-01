package com.library.DatabaseTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.library.DAO.IBookDAO;
import com.library.DAOImpl.BookDAOImpl;
import com.library.POJO.Book;


public class BookTest {

	IBookDAO bookDAO = new BookDAOImpl();
	
	@Test
	public void getBookByIDTest() {
		Book book = bookDAO.getBookByID(1001);
		assertEquals(269,book.getISBN());
	}
	
	@Test
	public void getBookByISBNTest() {
		Book book = bookDAO.getBookByISBN(269);
		assertEquals("Ptrick Ruthfus", book.getAuthor());
	}
	
	@Test
	public void getBookByTitle() {
		ArrayList<Book> books = bookDAO.getBookByTitle("The girl");
		assertEquals("The girl who played with fire",books.get(0).getTitle());
	}
	
	@Test
	public void getBookByAuthorTest() {
		ArrayList<Book> books = bookDAO.getBooksByAuthor("Ptrick Ruthfus");
		assertEquals("Ptrick Ruthfus",books.get(0).getAuthor());
	}
	
	@Test
	public void getBookByPublisherTest() {
		ArrayList<Book> books = bookDAO.getBookByPublisher("Shrivastav Pubilication");
		assertEquals("Shrivastav Pubilication",books.get(0).getPublisher());
	}
	
	@Test
	public void getBookByDescriptionTest() {
		ArrayList<Book> books = bookDAO.getBookByDescription("Horroe");
		assertEquals("Based on horroe movie",books.get(0).getDescription());
	}
	
	@Test
	public void getBookTitleTest() {
		String bookTitle = bookDAO.getBookTitle(1001);
		assertEquals("The girl who played with fire",bookTitle);
	}
}
