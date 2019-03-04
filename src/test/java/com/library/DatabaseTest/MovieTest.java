package com.library.DatabaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.library.BusinessModels.Movie;
import com.library.DAO.MovieDAO;
import com.library.IDAO.IMovieDAO;

public class MovieTest {

	IMovieDAO iMovieDAO = new MovieDAO();
	

	@Test
	public void getMovieByIdTest() {
		Movie movie = iMovieDAO.getMovieById(2001);
		assertEquals("Interstellar", movie.getTitle());
	}

	@Test
	public void getMovieByTitleTest() {
		Movie movie = iMovieDAO.getMovieByTitle("Interstellar");
		assertEquals("Christopher Nolan", movie.getDirector());
	}
	
	@Test
	public void getMoviesByDirectorNameTest()
	{
		List<Movie> listOfMoviesByDirectorName = iMovieDAO.getMoviesByDirectorName("Christopher Nolan");
		assertEquals("Interstellar", listOfMoviesByDirectorName.get(0).getTitle());
		assertEquals("Inception", listOfMoviesByDirectorName.get(1).getTitle());
	}
	
	@Test
	public void getMoviesByCategoryTest()
	{
		List<Movie> listOfMoviesByCategory = iMovieDAO.getMoviesByCategory("Romance");
		assertEquals("Eternal Sunshine of the Spotless Mind", listOfMoviesByCategory.get(0).getTitle());
	}
	
	@Test
	public void getMoviesByDescriptionTest()
	{
		List<Movie> listOfMoviesByCategory = iMovieDAO.getMoviesByCategory("Romance");
		assertEquals("Eternal Sunshine of the Spotless Mind", listOfMoviesByCategory.get(0).getTitle());
	}
	
	@Test
	public void createMovieTest()
	{
		Movie movie = new Movie();
		movie.setItemID(2006);
		movie.setCategory("Action");
		movie.setDescription(
				"Arthur Curry, the human-born heir to the underwater kingdom of Atlantis, goes on a quest to prevent a war between the worlds of ocean and land.");
		movie.setDirector("James Wan");
		movie.setTitle("Aquaman");
		movie.setAvailability(6);
		assertTrue(iMovieDAO.createMovie(movie));
	}
	
	@Test
	public void updateMovieTest()
	{
		Movie movie = new Movie();
		movie.setItemID(2004);
		movie.setCategory("Action");
		movie.setDescription(
				"Arthur Curry, the human-born heir to the underwater kingdom of Atlantis, goes on a quest to prevent a war between the worlds of ocean and land.");
		movie.setDirector("Christopher Nolan");
		movie.setTitle("Inception");
		movie.setAvailability(7);
		assertTrue(iMovieDAO.updateMovie(movie));
	}
	
	@Test
	public void deleteMovieTest()
	{
		Movie movie = new Movie();
		movie.setItemID(2006);
		assertTrue(iMovieDAO.deleteMovie(movie));
	}
}
