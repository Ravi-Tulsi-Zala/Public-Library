package com.library.welcomePage;

import java.sql.SQLException;
import java.util.List;

import com.library.BussinessModelSetter.DisplaySetter;
import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.ILibraryItemDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Display;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class WelcomePageController implements IWelcomeController {
	private ILibraryItemDAO libraryFactory;

	public WelcomePageController() {
		DAOFactory factory = new DAOFactory();
		libraryFactory = factory.makeLibraryItemDAO();
	}

	public boolean isAdminAvailable() {
		return AdminPage.getAdminAvailable();
	}

	public List<Book> getBookItems() throws SQLException {
		List<Book> books = null;
		books = libraryFactory.getTopBooks();
		DisplaySetter ds = new DisplaySetter();
		List<Display> d = ds.getBookDisplayObjects(books);
		for (int i = 0; i < books.size(); i++) {
			books.get(i).setCoverImageUrl(d.get(i).getImage());
		}
		return books;
	}

	public List<Movie> getMovieItems() throws SQLException {
		List<Movie> movie = null;
		movie = libraryFactory.getTopMovies();
		DisplaySetter ds = new DisplaySetter();
		List<Display> d = ds.getMovieDisplayObjects(movie);
		for (int i = 0; i < movie.size(); i++) {
			movie.get(i).setCoverImageUrl(d.get(i).getImage());
		}
		return movie;
	}

	public List<Music> getMusicItems() throws SQLException {
		List<Music> music = null;
		music = libraryFactory.getTopMusic();
		DisplaySetter ds = new DisplaySetter();
		List<Display> d = ds.getMusicDisplayObjects(music);
		for (int i = 0; i < music.size(); i++) {
			music.get(i).setCoverImageUrl(d.get(i).getImage());
		}
		return music;
	}

}
