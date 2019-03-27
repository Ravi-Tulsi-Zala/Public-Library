package com.library.itemDescription;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Display;
import com.library.businessModels.Movie;

public class MovieDescription implements IDescription{

	Movie movie;
	String cover;
	
	public MovieDescription(Display display) {
		DAOFactory factory = new DAOFactory();
		IMovieDAO movieDAO = factory.makeMovieDAO();
		int itemID = display.getItemID();
		movie = movieDAO.getMovieById(itemID);
		cover = display.getImage();
	}

	public String getCover() {
		return cover;
	}

	public String getDescription() 
	{
		return null;
	}
}
