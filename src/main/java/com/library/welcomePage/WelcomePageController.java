package com.library.welcomePage;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.ILibraryItemDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class WelcomePageController implements IWelcomeController {
	ILibraryItemDAO libraryFactory;

	public WelcomePageController() {
		DAOFactory factory = new DAOFactory();
		libraryFactory = factory.makeLibraryItemDAO();
	}
	
	public boolean isAdminAvailable() {
		return AdminPage.getAdminAvailable();
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
