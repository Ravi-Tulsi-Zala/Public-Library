package com.library.DAOImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		Book book = new Book();
		
		try
		{
			query = "SELECT Item_ID,ISBN, Title FROM books WHERE Item_ID = '" + itemID + "'"; 
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			
			if(!resultSet.next())
			{
				return null;
			}
			
			book.setISBN(resultSet.getInt("ISBN"));
			book.setItemID(resultSet.getInt("Item_ID"));
			book.setTitle(resultSet.getString("Title"));
			
			
			return book;
	        
			
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBookByISBN(int bookISBN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getBookByTitle(String bookTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getBooksByAuthor(String bookAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getBookByPublisher(String bookPublisher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getBookByDescription(String bookDescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBookTitle(int itemID) {
		// TODO Auto-generated method stub
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
	
}
