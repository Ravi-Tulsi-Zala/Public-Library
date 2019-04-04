package com.library.itemDetailed;

import com.library.BussinessModelSetter.DetailedDisplaySetter;
import com.library.BussinessModelSetter.IDetailedDisplaySetter;
import com.library.DAO.IBookDAO;
import com.library.DAO.IMovieDAO;
import com.library.DAO.IMusicDAO;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.businessModels.Book;
import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class DetailedDisplayFetcher implements IDetailedDisplayFetcher{

	private IDetailedDisplaySetter detailedDisplaySetter;
	private DisplayDetailed displayDetailed = null;
	private IDAOFactory factory;
	
	public DetailedDisplayFetcher() {
		detailedDisplaySetter = new DetailedDisplaySetter();
		factory = new DAOFactory();
	}
	
	@Override
	public DisplayDetailed fetchDetailedDisplay(String itemType, int itemID) {
		
		if(itemType.equals("Book"))
		{
			IBookDAO bookDAO = factory.makeBookDAO();
			Book book = bookDAO.getBookByID(itemID);
			displayDetailed = detailedDisplaySetter.makeDetailedBook(book);
		}
		else if(itemType.equals("Movie"))
		{
			IMovieDAO movieDAO = factory.makeMovieDAO();
			Movie movie = movieDAO.getMovieById(itemID);
			displayDetailed = detailedDisplaySetter.makeDetailedMovie(movie);
		}
		else if(itemType.equals("Music"))
		{
			IMusicDAO musicDAO = factory.makeMusicDAO();
			Music music = musicDAO.getMusicById(itemID);
			displayDetailed = detailedDisplaySetter.makeDetailedMusic(music);
		}
		return displayDetailed;
	}

}
