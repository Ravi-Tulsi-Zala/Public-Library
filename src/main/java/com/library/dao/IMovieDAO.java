package com.library.dao;

import java.util.List;

import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.search.MovieSearch;

public interface IMovieDAO {
	public Movie getMovieById(int itemID);
	public List<Movie> getMoviesByCategory(String category);
	public int createMovie(Movie movie);
	public Boolean updateMovie(Movie movie);
	public Boolean deleteMovie(Movie movie);
	public List<LibraryItem> getMoviesBySearchTerms(MovieSearch requestDetails, String searchTerms);
	public List<String> getMovieCategories();
	public Boolean getAvailability(int itemID); 
	public boolean checkMovieDuplicacy(Movie movie);
	public Boolean increaseCount(int itemID);
	public void increaseAvailability(String title);
	public void decreaseAvailability(String title);
	
}