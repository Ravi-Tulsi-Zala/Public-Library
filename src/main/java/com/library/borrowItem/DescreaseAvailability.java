package com.library.borrowItem;

import com.library.dao.IBookDAO;
import com.library.dao.IMovieDAO;
import com.library.dao.IMusicDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;

public class DescreaseAvailability {
	
	static final String book = "Book";
	static final String movie = "Movie";
	static final String music = "Music";
	static final int quantity = -1;
	String category;
	IDAOFactory factory;

	public DescreaseAvailability(String category) {
		this.category = category;
		factory = new DAOFactory();
	}
	
	public void decreaseAvailability(int itemID)
	{
		if(category.equals(book))
		{
			IBookDAO bookDAO = factory.makeBookDAO();
			bookDAO.updateAvailability(itemID, -1);
		}
		else if (category.equals(movie))
		{
			IMovieDAO movieDAO = factory.makeMovieDAO();
			movieDAO.updateAvailability(itemID, quantity);
		}
		else if (category.equals(music))
		{
			IMusicDAO musicDAO = factory.makeMusicDAO();
			musicDAO.updateAvailability(itemID, quantity);
		}
	}
}
