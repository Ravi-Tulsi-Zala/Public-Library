package com.library.DAO;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;
import com.library.BussinessModelSetter.BookSetter;
import com.library.IBussinessModelSetter.IBookSetter;
import com.library.dbConnection.*;
import com.library.search.BookSearch;

public class BookDAO implements IBookDAO {

	private PreparedStatement preparedStatement;
	private ResultSet resultSet = null;
	private String query;
	private Connection connection;
	private IBookSetter bookMapper = new BookSetter();
	private static final Logger logger = LogManager.getLogger(BookDAO.class);
	DatabaseConnection databaseConnection;

	public BookDAO() {

		try {
			databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			logger.log(Level.ALL, "Unable to connect to database", e);
		}
	}

	@Override
	public Book getBookByID(int itemID) {
		try {
			Book book = new Book();
			this.connection = databaseConnection.getConnection();
			query = "SELECT * FROM books WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			resultSet = preparedStatement.executeQuery();
			List<Book> books = bookMapper.mapBook(resultSet);
			book = books.get(0);
			return book;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Book not found for the specific Itemid", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return null;
	}

	@Override
	public List<Book> getBookByCategory(String category) {
		try {
			List<Book> books = new ArrayList<Book>();
			this.connection = databaseConnection.getConnection();
			query = "SELECT * FROM books WHERE Category=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, category);
			resultSet = preparedStatement.executeQuery();
			books = bookMapper.mapBook(resultSet);
			return books;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Book for this category", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return null;
	}

	@Override
	public List<String> getBookCategories() {
		List<String> categories = new ArrayList<String>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT Distinct Category from books";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categories.add(resultSet.getString("Category"));
			}
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Book Categories", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return categories;
	}

	private String prepareSearchQuery(BookSearch requestDetails, String searchTerms) {

		if (0 == searchTerms.length()) {
			logger.log(Level.ALL, "No search terms are supplied");
			return null;
		}

		String query = "SELECT DISTINCT * FROM books WHERE ";
		String[] searchterms = searchTerms.split("\\s");
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
	public List<LibraryItem> getBooksBySearchTerms(BookSearch requestDetails, String searchTerms) {
		List<LibraryItem> books = new LinkedList<LibraryItem>();
		List<Book> tempBooks = new ArrayList<>();
		this.connection = databaseConnection.getConnection();
		if (!requestDetails.isSearchInBooks()) {
			return books;
		}
		String query = prepareSearchQuery(requestDetails, searchTerms);

		if (null == query) {
			return books;
		}

		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			tempBooks = bookMapper.mapBook(resultSet);
			books.addAll(tempBooks);
			return books;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Failed to prepare SQL statement OR execute a query OR parse a query resultSet", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return books;
	}

	@Override
	public Boolean deleteBookByID(int itemID) {
		try {
			this.connection = databaseConnection.getConnection();
			query = "Delete FROM books WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete Book into database", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return false;
	}

	public Blob getCoverBlob(MultipartFile coverImage) {
		Blob coverBlob = null;
		try {
			byte[] bytes = coverImage.getBytes();
			coverBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
		} catch (IOException e) {

			logger.log(Level.ALL, "IO Exception while converting Multipart into Blob", e);

		} catch (SerialException e) {

			logger.log(Level.ALL, "Serial Exception while converting Multipart into Blob", e);

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch cover image", e);
		} finally {

			databaseConnection.closeConnection((com.mysql.jdbc.PreparedStatement) preparedStatement);

		}
		return coverBlob;
	}

	@Override
	public int createBook(Book book) {
		String category = book.getCategory();
		String title = book.getTitle();
		String author = book.getAuthor();
		int isbn = book.getIsbn();
		String publisher = book.getPublisher();
		String description = book.getDescription();
		int availablity = book.getAvailability();
		int recentlyAddedBookId = 0;

		try {
			this.connection = databaseConnection.getConnection();
			query = "Insert into books (Category,Title,Author,ISBN,Publisher,Description,Availability) Values "
					+ "(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, category);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, author);
			preparedStatement.setInt(4, isbn);
			preparedStatement.setString(5, publisher);
			preparedStatement.setString(6, description);
			preparedStatement.setInt(7, availablity);
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				recentlyAddedBookId = resultSet.getInt(1);
			}

			return recentlyAddedBookId;

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert Book into database", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return recentlyAddedBookId;
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
			this.connection = databaseConnection.getConnection();
			query = "Update books  set Category = ?, Title = ?, Author = ?, ISBN =  ?,"
					+ "Publisher = ?, Description = ?, Availability = ? WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, category);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, author);
			preparedStatement.setInt(4, isbn);
			preparedStatement.setString(5, publisher);
			preparedStatement.setString(6, description);
			preparedStatement.setInt(7, availablity);
			preparedStatement.setInt(8, itemID);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update Book into database", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return false;
	}

	@Override
	public int getAvailability(int itemID) {
		
		int booksAvailable = 0;
		try {
			this.connection = databaseConnection.getConnection();
			query = "Select Availability from books where Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				booksAvailable = resultSet.getInt("Availability");
			}
			
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the availability of Book", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return booksAvailable;
	}

	public boolean checkBookDuplicacy(Book book) {
		String authorToBeAdded = book.getAuthor();
		String titleToBeAdded = book.getTitle();
		boolean isBookAvailable = false;

		query = "SELECT * FROM books where Title=? and Author=?";
		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, titleToBeAdded);
			preparedStatement.setString(2, authorToBeAdded);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isBookAvailable = true;
			} else {
				isBookAvailable = false;
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Books", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return isBookAvailable;
	}

	public Boolean increaseCount(int itemID) {
		Boolean countIncrease = false;
		try {
			this.connection = databaseConnection.getConnection();
			query = "update books set availability =? where Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			preparedStatement.execute();
			countIncrease = true;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error increasing count of book", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);

		}
		return countIncrease;
	}

	@Override
	public void updateAvailability(int itemId, int updatedAvailability) {
		
		try {
			this.connection = databaseConnection.getConnection();
			query = "update books set Availability =? where Item_Id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, updatedAvailability);
			preparedStatement.setInt(2, itemId);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error increasing availability of book", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		

	}

}
