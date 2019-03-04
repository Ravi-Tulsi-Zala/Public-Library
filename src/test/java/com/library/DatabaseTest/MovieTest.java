package com.library.DatabaseTest;

import static org.junit.Assert.assertEquals;

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
		assertEquals(2001, movie.getItemID());
	}

}
