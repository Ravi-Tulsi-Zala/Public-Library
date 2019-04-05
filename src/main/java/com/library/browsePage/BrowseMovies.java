package com.library.browsePage;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IBussinessModelSetter.IDisplaySetter;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Display;
import com.library.businessModels.Movie;
import com.library.BussinessModelSetter.DisplaySetter;

public class BrowseMovies implements IBrowseDisplayObjects{
	
	private IMovieDAO movieDAO;
	private String itemType;
	private static String movie = "Movie";
	
	public BrowseMovies()
	{
		IDAOFactory factory = new DAOFactory();
		movieDAO = factory.makeMovieDAO();	
		itemType = "movie";
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

	@Override
	public String getItemType() {
		return itemType;
	}

}
