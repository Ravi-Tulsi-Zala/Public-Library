package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.BussinessModelSetter.BookSetter;
import com.library.IBussinessModelSetter.IBookSetter;
import com.library.dbConnection.*;
import com.library.search.IBookSearchRequestDetails;

public class BookDAO implements IBookDAO {

	private PreparedStatement preparedStatement;
	private String query;
	private Connection connection;
	private IBookSetter bookMapper = new BookSetter();
	private static final Logger logger = LogManager.getLogger(BookDAO.class);
	
	public BookDAO() {

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			logger.log(Level.ALL, "Unable to connect to database", e);
		}
	 }
	

	@Override
	public Book getBookByID(int itemID) {
		try {
			Book book = new Book();
			query = "SELECT * FROM books WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			book = bookMapper.mapBook(resultSet);
			return book;
		}	
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Book not found for the specific Itemid", e);
		}
		return null;
	}

	private String prepareSearchQuery(IBookSearchRequestDetails requestDetails) {

		if (0 == requestDetails.getSearchTerms().length()) {
			logger.log(Level.ALL, "No search terms are supplied");
			return null;
		}

		String query = "SELECT DISTINCT * FROM books WHERE ";
		String[] searchterms = requestDetails.getSearchTerms().split("\\s");
		for (String term : searchterms) {
			if (requestDetails.isSearchBookAuthor()) {
				query += "Author like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchBookDescription()) {
				query += "Description like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchBookISBN()) {
				query += "ISBN like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchBookPublisher()) {
				query += "Publisher like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchBookTitle()) {
				query += "Title like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchBookCategory()) {
				query += "Category like \"%" + term + "%\" or ";
			}
		}

		query = query.substring(0, query.length() - 4);
		return query;
	}

	@Override
	public LinkedList<Book> getBooksBySearchTerms(IBookSearchRequestDetails searchRequestDetails) {
		LinkedList<Book> books = new LinkedList<Book>();
		Book book;
		String query = prepareSearchQuery(searchRequestDetails);

		if(null ==query) {
			return books;
		}
		
		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			}

			return books;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Failed to prepare SQL statement OR execute a query OR parse a query resultSet", e);
		}
		
		return books;
	}

	@Override
	public Boolean deleteBookByID(int itemID) {
		try {
			query = "Delete FROM books WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			preparedStatement.executeUpdate();
			return true;
		}	
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete Book into database", e);
		}
		return false;
	}

	@Override
	public Boolean createBook(Book book) {
		String category = book.getCategory();
		String title = book.getTitle();
		String author = book.getAuthor();
		int isbn = book.getIsbn();
		String publisher = book.getPublisher();
		String description = book.getDescription();
		int availablity = book.getAvailability();
		try {

			query = "Inser into books (Category,Title,Author,ISBN,Publisher,Description,Availability) Values "
					+ "(?,?,?,?,?,?,?)";
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1, category);
			 preparedStatement.setString(2, title);
			 preparedStatement.setString(3, author);
			 preparedStatement.setInt(4, isbn);
			 preparedStatement.setString(5, publisher);
			 preparedStatement.setString(6, description);
			 preparedStatement.setInt(7, availablity);
			 preparedStatement.executeUpdate();
			 return true;
		 }
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert Book into database", e);
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
		String description = book.getDescription();
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
			 preparedStatement.executeUpdate();
			 return true;
		 }
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update Book into database", e);
		}
		return false;
	}

	@Override
	public List<Book> getBookByCategory(String category) {
		try {
			List<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books WHERE Category=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, category);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			do {
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while (resultSet.next());
			return books;
		}	
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Book for this category", e);
		}
		return null;
	}
	
	@Override
	public List<Book> getTopBooks() {
		try {
			List<Book> books = new ArrayList<Book>();
			Book book = new Book();
			query = "SELECT * FROM books order by books.Item_ID desc limit 2";
			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			do {
				book = bookMapper.mapBook(resultSet);
				books.add(book);
			} while (resultSet.next());
			return books;
		}	
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Book for this category", e);
		}
		return null;
	}
	
	@Override
	public List<String> getBookCategories()
	{
		List<String> categories = new ArrayList<String>();
		query = "SELECT Category from books";
		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			do {
				categories.add(resultSet.getString("Category"));
			} while (resultSet.next());
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Book Categories", e);
		}
		
		return categories;
	}
}
