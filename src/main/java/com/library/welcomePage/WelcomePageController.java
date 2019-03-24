package com.library.welcomePage;

import java.util.ArrayList;
import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.IDAO.ILibraryItemDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.controllers.ILibraryFactory;

public class WelcomePageController {
	ILibraryItemDAO libraryFactory;

	public WelcomePageController() {
		DAOFactory factory = new DAOFactory();
		libraryFactory = factory.makeLibraryItemDAO();
	}

	public List<Book> getBookItems() {

		List<Book> books = libraryFactory.getTopBooks();
		return books;
	}

	public List<Movie> getMovieItems() {
		List<Movie> movie = libraryFactory.getTopMovies();
		return movie;
	}

	public List<Music> getMusicItems() {
		List<Music> music = libraryFactory.getTopMusic();
		return music;
	}
}
