package com.library.databaseTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.DAO.BookDAO;


public class BookTest {

	IBookDAO bookDAO = new BookDAO();
	
	@Test
	public void getBookByIDTest() {
		Book book = bookDAO.getBookByID(100001);
		assertEquals(269,book.getIsbn());
	}
	
	
	@Test
	public void deleteBookTest()
	{
		Boolean isBookDeleted = bookDAO.deleteBookByID(100002);
		assertEquals(true,isBookDeleted);
	}

	@Test
	public void createBookTest()
	{
		Book book = new Book();
		book.setAuthor("Albert Camus");
		book.setAvailability(1);
		book.setCategory("Philoshophy");
		book.setDescription("How sysphus loves rolling the bolder to the mountain top");
		book.setIsbn(265);
		book.setPublisher("Paris publication");
		book.setTitle("Myth of sysphus");
		Boolean isBookCreated = bookDAO.createBook(book);
		assertEquals(true,isBookCreated);
	}
	
	@Test
	public void updateBookTest() {
		Book book = new Book();
		book.setAuthor("Albert Camus");
		book.setAvailability(1);
		book.setCategory("Philoshophy");
		book.setDescription("How sysphus loves rolling the bolder to the mountain top");
		book.setIsbn(265);
		book.setItemID(1002);
		book.setPublisher("Swedan publication");
		book.setTitle("Myth of sysphus");
		Boolean isBookUpdated = bookDAO.updateBook(book);
		assertEquals(true,isBookUpdated);
	}
	
	@Test
	public void getBookByCategoryTest()
	{
		List<Book> books = bookDAO.getBookByCategory("Horror");
		assertEquals("Horror",books.get(0).getCategory());
	}
}
