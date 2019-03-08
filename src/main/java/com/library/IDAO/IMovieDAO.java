package com.library.IDAO;


import java.util.List;

import com.library.businessModels.Movie;

public interface IMovieDAO {
	
	public Movie getMovieById(int itemID);
	public Movie getMovieByTitle(String movieTitle);
	public List<Movie> getMoviesByDirectorName(String directorName);
	public List<Movie> getMoviesByCategory(String category);
	public List<Movie> getMoviesByDescription(String movieDescription);
	public Boolean createMovie(Movie movie);
	public Boolean updateMovie(Movie movie);
	public Boolean deleteMovie(Movie movie); 
	
	
}
