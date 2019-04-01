package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.library.BussinessModelSetter.MovieSetter;
import com.library.IBussinessModelSetter.IMovieSetter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.dbConnection.DatabaseConnection;
import com.library.search.MovieSearch;

public class MovieDAO implements IMovieDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	IMovieSetter movieSetter = new MovieSetter();
	private static final Logger logger = LogManager.getLogger(MovieDAO.class);
	DatabaseConnection databaseConnection;
	ResultSet resultSet;

	public MovieDAO() {

		try {
			databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {

			logger.log(Level.ALL, "Unable to connect to database", e);
		}
	}

	@Override
	public Movie getMovieById(int itemID) {

		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			resultSet = preparedStatement.executeQuery();
			movies = movieSetter.mapMovie(resultSet);
			movie = movies.get(0);
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {

			logger.log(Level.ALL, "Movie not found for the specific Itemid", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return movie;
	}

	@Override
	public List<Movie> getMoviesByCategory(String category) {

		query = "SELECT * from movie WHERE Category LIKE ?";
		List<Movie> moviesByCategory = new ArrayList<Movie>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + category + "%");
			resultSet = preparedStatement.executeQuery();
			moviesByCategory = movieSetter.mapMovie(resultSet);

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of movies for this category", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return moviesByCategory;
	}

	@Override
	public int createMovie(Movie movie) {

		int recentlyAddedMovieId = 0;
		String movieCategory = movie.getCategory();
		String movieTitle = movie.getTitle();
		String movieDirector = movie.getDirector();
		String movieDescription = movie.getDescription();
		int movieAvailability = movie.getAvailability();

		try {
			query = "INSERT INTO movie (Category,Title,Director,Description,Availability) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, movieCategory);
			preparedStatement.setString(2, movieTitle);
			preparedStatement.setString(3, movieDirector);
			preparedStatement.setString(4, movieDescription);
			preparedStatement.setInt(5, movieAvailability);
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				recentlyAddedMovieId = resultSet.getInt(1);
			}

			return recentlyAddedMovieId;

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert movie into database", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return recentlyAddedMovieId;
	}

	@Override
	public Boolean updateMovie(Movie movie) {

		String movieCategory = movie.getCategory();
		String movieTitle = movie.getTitle();
		String movieDirector = movie.getDirector();
		String movieDescription = movie.getDescription();
		int movieAvailability = movie.getAvailability();
		int movieItemId = movie.getItemID();

		try {
			query = "UPDATE movie SET Category=?,Title=?,Director=?,Description=?,Availability=? WHERE Item_ID=? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieCategory);
			preparedStatement.setString(2, movieTitle);
			preparedStatement.setString(3, movieDirector);
			preparedStatement.setString(4, movieDescription);
			preparedStatement.setInt(5, movieAvailability);
			preparedStatement.setInt(6, movieItemId);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update movie into database", e);
		}
		finally
		{
			databaseConnection.closeConnection((com.mysql.jdbc.PreparedStatement) preparedStatement);
		}
		return false;
	}

	@Override
	public Boolean deleteMovie(Movie movie) {

		int movieItemId = movie.getItemID();

		try {
			query = "DELETE from movie WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movieItemId);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete movie into database", e);
		}
		finally
		{
			databaseConnection.closeConnection((com.mysql.jdbc.PreparedStatement) preparedStatement);
		}
		return false;
	}

	private String prepareSearchQuery(MovieSearch requestDetails, String searchTerms) {

		if (0 == searchTerms.length()) {
			logger.log(Level.ALL, "No search terms are supplied");
			return null;
		}

		String query = "SELECT DISTINCT * FROM movie WHERE ";
		String[] searchterms = searchTerms.split("\\s");
		for (String term : searchterms) {
			if (requestDetails.isSearchMovieTitle()) {
				query += "Title like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchMovieDirector()) {
				query += "Director like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchMovieDescription()) {
				query += "Description like \"%" + term + "%\" or ";
			}
		}
		query = query.substring(0, query.length() - 4);
		return query;
	}

	@Override
	public List<LibraryItem> getMoviesBySearchTerms(MovieSearch requestDetails, String searchTerms) {
		List<Movie> tempMovie = new ArrayList<>();
		List<LibraryItem> movies = new LinkedList<LibraryItem>();
		if(!requestDetails.isSearchInMovies()) {
			return movies;
		}
		String query = prepareSearchQuery(requestDetails, searchTerms);
		
		if(null == query) {
			return movies;
		}

		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			tempMovie = movieSetter.mapMovie(resultSet);
			movies.addAll(tempMovie);
			return movies;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Failed to prepare SQL statement OR execute a query OR parse a query resultSet", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return movies;
	}

	
	@Override
	public List<String> getMovieCategories()
	{
		List<String> categories = new ArrayList<String>();
		query = "SELECT Distinct Category from movie";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				categories.add(resultSet.getString("Category"));
			}
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Movie Categories", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return categories;
	}
	
	@Override
	public Boolean getAvailability(int itemID)
	{
		Boolean availability = false;
		int moviesAvailable = 0; 
		try {
			query = "Select Availability from movie where Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(0,itemID);
			resultSet = preparedStatement.executeQuery();
			moviesAvailable = resultSet.getInt(0);
		}	
		catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the availability of Movie", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		
		if(moviesAvailable>0)
		{
			availability = true;
		}
		return availability;
	}

	public boolean checkMovieDuplicacy(Movie movie) {
		String directorToBeAdded = movie.getDirector();
		String titleToBeAdded = movie.getTitle();
		boolean isMovieAvailable = false;

		query = "SELECT * FROM movie where Title=? and Director=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, titleToBeAdded);
			preparedStatement.setString(2, directorToBeAdded);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isMovieAvailable = true;
			} else {
				isMovieAvailable = false;
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Movies", e);
		}
		finally
		{
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return isMovieAvailable;
	}

}
