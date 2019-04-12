package com.library.browsePage;

import java.util.List;

import com.library.businessModelSetter.DisplaySetter;
import com.library.businessModelSetter.IDisplaySetter;
import com.library.businessModels.Display;
import com.library.businessModels.Movie;
import com.library.dao.IMovieDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;

public class BrowseMovies implements IBrowseDisplayComponent{
	
	private IMovieDAO movieDAO;
	
	public BrowseMovies()
	{
		IDAOFactory factory = new DAOFactory();
		movieDAO = factory.makeMovieDAO();	
	}

	@Override
	public List<Display> itemsByCategory(String category) {
		IDisplaySetter displaySetter = new DisplaySetter();
		List<Movie> movies = movieDAO.getMoviesByCategory(category);
		List<Display> displayMovies = displaySetter.getMovieDisplayObjects(movies);
		return displayMovies;
	}

	@Override
	public List<String> getCategories() {
		List<String> categories = movieDAO.getMovieCategories();
		return categories;
	}

}
