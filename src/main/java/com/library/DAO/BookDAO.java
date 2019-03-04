package com.library.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.BusinessModels.Book;
import com.library.IDAO.IBookDAO;
import com.library.DAOMapper.IBookMapper;
import com.library.DAOMapperImpl.BookMapper;
import com.library.dbConnection.*;

public class BookDAO implements IBookDAO {
	
	private PreparedStatement preparedStatement;
	private String query;
	private Connection connection;
	private IBookMapper bookMapper = new BookMapper();
	
	 public BookDAO(){

		 try
		 {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		}
		 catch (Exception e) {
			 e.printStackTrace();
		}
	 }
	
	@Override
	public Book getBookByID(int itemID) {
		try
		{
			Book book = new Book();
			query = "SELECT * FROM books WHERE Item_ID = '" + itemID + "'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			
			if(!resultSet.next())
			{
				return null;
			}
			book = bookMapper.mapBook(resultSet);		
			return book;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBookByISBN(int bookISBN) {
		
		try
		{
			Book book = new Book();
			query = "SELECT * FROM books WHERE ISBN = '" + bookISBN + "'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			
			book = bookMapper.mapBook(resultSet);
			return book;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBookByTitle(String bookTitle) {
		
		try {
			List<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books WHERE Title like '%" + bookTitle + "%'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			do
			{
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while(resultSet.next());
			return books;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBooksByAuthor(String bookAuthor) {
		try {
			List<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books WHERE Author like '%" + bookAuthor + "%'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			do
			{
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while(resultSet.next());
			return books;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBookByPublisher(String bookPublisher) {
		try {
			List<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books WHERE Publisher like '%" + bookPublisher + "%'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			do
			{
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while(resultSet.next());
			return books;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBookByDescription(String bookDescription) {
		try {
			ArrayList<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books WHERE Description like '%" + bookDescription + "%'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next())
			{
				return null;
			}
			do
			{
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while(resultSet.next());
			return books;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getBookTitle(int itemID) {
		try
		{
			query = "SELECT Title FROM books WHERE Item_ID = '" + itemID + "'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			String title = resultSet.getString("Title");
			return  title;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Book> getBookByKeyword(String keyword) {
		try {
			List<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books WHERE Title like '%" + keyword + "%' or Author like '%" + keyword + "%' or Publisher like '%" + keyword + "%' or Description like '%" + keyword + "%'" ; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			do
			{
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while(resultSet.next());
			return books;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deleteBookByID(int itemID) {
		try
		{
			query = "Delete FROM books WHERE Item_ID = '" + itemID + "'";
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.executeUpdate();	
			changeBooksItemID(itemID);
			return true;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean createBook(Book book) {
		String category = book.getCategory();
		String title = book.getTitle();
		String author = book.getAuthor();
		int ISBN = book.getISBN();
		String publisher = book.getPublisher();
		String description =  book.getDescription();
		int itemID = getLastID() + 1;
		int availablity = book.getAvailablity();
		query = "Insert into books (Item_ID,Category,Title,Author,ISBN,Publisher,Description,Availability) Values"
				+ " ('" + itemID + "','" + category + "','" + title + "','" + author + "'," + ISBN + ",'" 
				+ publisher + "','" + description + "'," + availablity + ")";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.executeUpdate(query);
			 return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return false;
	}
	
	@Override
	public Boolean updateBook(Book book) {
		String category = book.getCategory();
		String title = book.getTitle();
		String author = book.getAuthor();
		int ISBN = book.getISBN();
		String publisher = book.getPublisher();
		String description =  book.getDescription();
		int itemID = book.getItemID();
		int availablity = book.getAvailablity();
		query = "Update books  set Category = '" + category + "', Title = '" + title + "', Author = '" + author + "', ISBN =  '" + ISBN
				+ "', Publisher = '" + publisher + "', Description = '" + description + "', Availability = '" + availablity + "'WHERE Item_ID =" + itemID;
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.executeUpdate(query);
			 return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return false;
	}

	@Override
	public int getLastID() {
		try
		{
			query = "SELECT Item_ID from books where Item_ID = (SELECT MAX(Item_ID) FROM books)"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return 100000;
			}
			return resultSet.getInt("Item_ID"); 
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void changeBooksItemID(int itemID) {
		try
		{
			query = "Update books set Item_ID = Item_ID - 1 where Item_ID > " + itemID;
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.executeUpdate();	
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
	