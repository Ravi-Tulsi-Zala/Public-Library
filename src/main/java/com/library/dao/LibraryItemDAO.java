package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.bussinessModelSetter.BookSetter;
import com.library.bussinessModelSetter.IBookSetter;
import com.library.bussinessModelSetter.IMovieSetter;
import com.library.bussinessModelSetter.IMusicSetter;
import com.library.bussinessModelSetter.MovieSetter;
import com.library.bussinessModelSetter.MusicSetter;
import com.library.dbConnection.DatabaseConnection;

public class LibraryItemDAO implements ILibraryItemDAO {
	private PreparedStatement preparedStatement;
	private String query;
	private String limitNumber = "3";
	private Connection connection;
	private IBookSetter bookMapper = null;
	private IMovieSetter movieMapper = null;
	private IMusicSetter musicMapper = null;
	private static final Logger logger = LogManager.getLogger(BookDAO.class);
	DatabaseConnection databaseConnection;
	
	public LibraryItemDAO() {

		try {
			databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			logger.log(Level.ALL, "Unable to connect to database", e);
		}

	}

	@Override
	public List<Book> getLatestBooks() throws SQLException {
		bookMapper = new BookSetter();
		List<Book> books = new ArrayList<Book>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT distinct * FROM books order by books.Item_ID desc limit " + limitNumber;
		preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		books = bookMapper.mapBook(resultSet);
		return books;
	}

	@Override
	public List<Movie> getLatestMovies() throws SQLException {
		movieMapper = new MovieSetter();
		List<Movie> movies = new ArrayList<Movie>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT distinct * FROM movie order by movie.Item_ID desc limit " + limitNumber;
		preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		movies = movieMapper.mapMovie(resultSet);
		return movies;
	}

	@Override
	public List<Music> getLatestMusic() throws SQLException {
		musicMapper = new MusicSetter();
		List<Music> musicList = new ArrayList<Music>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT distinct * FROM music order by music.Item_ID desc limit " + limitNumber;
		preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		musicList = musicMapper.mapMusic(resultSet);
		return musicList;
	}

	@Override
	public List<Book> getFavouriteBooks() throws SQLException {
		bookMapper = new BookSetter();
		List<Book> books = new ArrayList<Book>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT distinct * FROM books order by Count desc limit " + limitNumber;
		preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		books = bookMapper.mapBook(resultSet);
		return books;
	}

	@Override
	public List<Movie> getFavouriteMovies() throws SQLException {
		movieMapper = new MovieSetter();
		List<Movie> movies = new ArrayList<Movie>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT distinct * FROM movie order by Count desc limit " + limitNumber;
		preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		movies = movieMapper.mapMovie(resultSet);
		return movies;
	}

	@Override
	public List<Music> getFavouriteMusic() throws SQLException {
		List<Music> musicList = new ArrayList<Music>();
		this.connection = databaseConnection.getConnection();
		query = "SELECT distinct * FROM music order by Count desc limit " + limitNumber;
		preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		musicList = musicMapper.mapMusic(resultSet);
		return musicList;
	}
}