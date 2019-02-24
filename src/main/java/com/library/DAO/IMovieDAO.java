package com.library.DAO;

import java.util.ArrayList;

import com.library.POJO.Movie;

public interface IMovieDAO {
	
	public Movie getMovieById(int itemID);
	public Movie getMovieByTitle(String movieTitle);
	public ArrayList<Movie> getMoviesByDirectorName(String directorName);
	public ArrayList<Movie> getMoviesByCategory(String category);
	public ArrayList<Movie> getMoviesByDescription(String movieDescription);
	public void createMovie(Movie movie);
	public void updateMovie(Movie movie);
	public void deleteMovie(Movie movie); 
	
	
}
