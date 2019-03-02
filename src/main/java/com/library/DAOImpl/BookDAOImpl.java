package com.library.DAOImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.library.BusinessModels.Book;
import com.library.DAO.IBookDAO;
import com.library.DAOMapper.BookMapper;
import com.library.dbConnection.*;

public class BookDAOImpl implements IBookDAO {
	
	
	private PreparedStatement preparedStatement;
	private String query;
	private Connection connection;
	private BookMapper bookMaper = new BookMapper();
	
	 public BookDAOImpl(){

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
			book = bookMaper.mapBook(resultSet);		
			book.setISBN(resultSet.getInt("ISBN"));
			book.setItemID(resultSet.getInt("Item_ID"));
			book.setTitle(resultSet.getString("Title"));
			book.setAuthor(resultSet.getString("Author"));
			book.setCategory(resultSet.getString("Category"));
			book.setDescription(resultSet.getString("Description"));
			book.setPublisher(resultSet.getString("Publisher"));
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
			
			book = bookMaper.mapBook(resultSet);
			return book;
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Book> getBookByTitle(String bookTitle) {
		
		try {
			ArrayList<Book> books = new ArrayList<Book>();
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
				book = bookMaper.mapBook(resultSet);
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
	public ArrayList<Book> getBooksByAuthor(String bookAuthor) {
		try {
			ArrayList<Book> books = new ArrayList<Book>();
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
				book = bookMaper.mapBook(resultSet);
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
	public ArrayList<Book> getBookByPublisher(String bookPublisher) {
		try {
			ArrayList<Book> books = new ArrayList<Book>();
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
				book = bookMaper.mapBook(resultSet);
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
	public ArrayList<Book> getBookByDescription(String bookDescription) {
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
				book = bookMaper.mapBook(resultSet);
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
			return resultSet.getString("Title"); 
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<Book> getBookByKeyword(String keyword) {
		try {
			ArrayList<Book> books = new ArrayList<Book>();
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
				book = bookMaper.mapBook(resultSet);
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
		int itemID = book.getItemID();
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

}
	