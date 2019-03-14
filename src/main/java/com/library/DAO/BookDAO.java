package com.library.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.DAOMapper.IBookMapper;
import com.library.DAOMapperImpl.BookMapper;
import com.library.dbConnection.*;
import com.library.search.IBookSearchRequestDetails;

public class BookDAO implements IBookDAO {
	
	private PreparedStatement preparedStatement;
	private String query;
	private Connection connection;
	private IBookMapper bookMapper = new BookMapper();
	private int firstBookNumber = 10000;
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
			query = "SELECT * FROM books WHERE Item_ID = ?";
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
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
			query = "SELECT * FROM books WHERE ISBN = ?"; 
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookISBN);
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
			query = "SELECT * FROM books WHERE Title like ?"; 
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+ bookTitle + "%");
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
			query = "SELECT * FROM books WHERE Author like ?"; 
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+bookAuthor+"%");
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
			query = "SELECT * FROM books WHERE Publisher like ?"; 
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+bookPublisher + "%");
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
			query = "SELECT * FROM books WHERE Description like ?"; 
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + bookDescription + "%");
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
			query = "SELECT Title FROM books WHERE Item_ID = ?"; 
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
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
	
	private void prepareSearchQuery(IBookSearchRequestDetails requestDetails) {
		query = "SELECT * FROM books WHERE ";
		String[] searchterms = requestDetails.getSearchTerms().split("\\s");
		for(String term : searchterms) {
			if(requestDetails.isSearchBookAuthor()) {
				query += "Author like \"%" + term + "%\" or ";
			}
			if(requestDetails.isSearchBookDescription()) {
				query += "Description like \"%" + term + "%\" or ";
			}
			if(requestDetails.isSearchBookISBN()) {
				query += "ISBN like \"%" + term + "%\" or ";
			}
			if(requestDetails.isSearchBookPublisher()) {
				query += "Publisher like \"%" + term + "%\" or ";
			}
			if(requestDetails.isSearchBookTitle()) {
				query += "Title like \"%" + term + "%\" or ";
			}
		}
		
		query = query.substring(0, query.length() - 4);
	}
	
	@Override
	public LinkedList<Book> getBooksBySearchTerms(IBookSearchRequestDetails searchRequestDetails) {
		LinkedList<Book> books = new LinkedList<Book>();
		Book book;
		prepareSearchQuery(searchRequestDetails);
		
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Boolean deleteBookByID(int itemID) {
		try
		{
			query = "Delete FROM books WHERE Item_ID = ?";
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
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
		int isbn = book.getIsbn();
		String publisher = book.getPublisher();
		String description =  book.getDescription();
		int itemID = getLastID() + 1;
		int availablity = book.getAvailability();
		try {
			
			query = "Insert into books (Item_ID,Category,Title,Author,ISBN,Publisher,Description,Availability) Values "
					+ "(?,?,?,?,?,?,?,?)";
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setInt(1, itemID);
			 preparedStatement.setString(2, category);
			 preparedStatement.setString(3, title);
			 preparedStatement.setString(4, author);
			 preparedStatement.setInt(5, isbn);
			 preparedStatement.setString(6, publisher);
			 preparedStatement.setString(7, description);
			 preparedStatement.setInt(8, availablity);
			 preparedStatement.executeUpdate();
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
		int isbn = book.getIsbn();
		String publisher = book.getPublisher();
		String description =  book.getDescription();
		int itemID = book.getItemID();
		int availablity = book.getAvailability();
		try {
			query = "Update books  set Category = ?, Title = ?, Author = ?, ISBN =  ?,"
					+ "Publisher = ?, Description = ?, Availability = ? WHERE Item_ID = ?";
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1,category);
			 preparedStatement.setString(2,title);
			 preparedStatement.setString(3, author);
			 preparedStatement.setInt(4, isbn);
			 preparedStatement.setString(5, publisher);
			 preparedStatement.setString(6, description);
			 preparedStatement.setInt(7, availablity);
			 preparedStatement.setInt(8, itemID);
			 System.out.println(preparedStatement);
			 preparedStatement.executeUpdate();
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
				return firstBookNumber;
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
			query = "Update books set Item_ID = Item_ID - 1 where Item_ID > ?";
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			preparedStatement.executeUpdate();	
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	