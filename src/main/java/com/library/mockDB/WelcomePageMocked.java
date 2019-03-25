package com.library.mockDB;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class WelcomePageMocked {
	private DAOFactory factory = null;

	public WelcomePageMocked() {
		factory = new DAOFactory();
	}

	public Movie initiateMovieMock() {
		Movie movie = new Movie();
		movie = factory.makeMovieDAO().getMovieById(2001);
		return movie;
	}

	public Book initiateBookMock() {
		Book book = new Book();
		book = factory.makeBookDAO().getBookByID(100001);
		return book;
	}

	public Music initiateMusicMock() {
		Music music = new Music();
		music = factory.makeMusicDAO().getMusicById(3001);
		return music;
	}

}
