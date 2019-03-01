package com.library.DAOImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.BusinessModels.Book;
import com.library.DAO.IBookDAO;
import com.library.dbConnection.*;

public class BookDAOImpl implements IBookDAO {
	
	
	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	
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
				book.setISBN(resultSet.getInt("ISBN"));
				book.setItemID(resultSet.getInt("Item_ID"));
				book.setTitle(resultSet.getString("Title"));
				book.setAuthor(resultSet.getString("Author"));
				book.setCategory(resultSet.getString("Category"));
				book.setDescription(resultSet.getString("Description"));
				book.setPublisher(resultSet.getString("Publisher"));
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
				book.setISBN(resultSet.getInt("ISBN"));
				book.setItemID(resultSet.getInt("Item_ID"));
				book.setTitle(resultSet.getString("Title"));
				book.setAuthor(resultSet.getString("Author"));
				book.setCategory(resultSet.getString("Category"));
				book.setDescription(resultSet.getString("Description"));
				book.setPublisher(resultSet.getString("Publisher"));
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
				book.setISBN(resultSet.getInt("ISBN"));
				book.setItemID(resultSet.getInt("Item_ID"));
				book.setTitle(resultSet.getString("Title"));
				book.setAuthor(resultSet.getString("Author"));
				book.setCategory(resultSet.getString("Category"));
				book.setDescription(resultSet.getString("Description"));
				book.setPublisher(resultSet.getString("Publisher"));
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
				book.setISBN(resultSet.getInt("ISBN"));
				book.setItemID(resultSet.getInt("Item_ID"));
				book.setTitle(resultSet.getString("Title"));
				book.setAuthor(resultSet.getString("Author"));
				book.setCategory(resultSet.getString("Category"));
				book.setDescription(resultSet.getString("Description"));
				book.setPublisher(resultSet.getString("Publisher"));
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
	public void deleteBookByID(int itemID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Book> getBooks(ArrayList<Integer> itemIDS) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String[] args) {
		
	}
	
	@Override
	public Boolean checkAvailability(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

}
	