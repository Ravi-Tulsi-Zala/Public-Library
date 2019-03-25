package com.library.welcomePage;

import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public interface IWelcomeController {
	public List<Book> getBookItems();

	public List<Movie> getMovieItems();

	public List<Music> getMusicItems();
	
	public boolean isAdminAvailable();
}
