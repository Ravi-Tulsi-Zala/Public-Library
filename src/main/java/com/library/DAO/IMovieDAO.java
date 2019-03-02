package com.library.DAO;


import java.util.List;

import com.library.BusinessModels.Movie;

public interface IMovieDAO {
	
	public Movie getMovieById(int itemID);
	public Movie getMovieByTitle(String movieTitle);
	public List<Movie> getMoviesByDirectorName(String directorName);
	public List<Movie> getMoviesByCategory(String category);
	public List<Movie> getMoviesByDescription(String movieDescription);
	public void createMovie(Movie movie);
	public void updateMovie(Movie movie);
	public void deleteMovie(Movie movie); 
	
	
}
