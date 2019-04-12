package com.library.itemDetailed;

import com.library.businessModelSetter.DetailedDisplaySetter;
import com.library.businessModelSetter.IDetailedDisplaySetter;
import com.library.businessModels.Book;
import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.dao.IBookDAO;
import com.library.dao.IMovieDAO;
import com.library.dao.IMusicDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
import com.library.loanmanagement.CategoryEnum;

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
		
		if(itemType.equals(CategoryEnum.BOOK.getText()))
		{
			IBookDAO bookDAO = factory.makeBookDAO();
			Book book = bookDAO.getBookByID(itemID);
			displayDetailed = detailedDisplaySetter.makeDetailedBook(book);
		}
		else if(itemType.equals(CategoryEnum.MOVIE.getText()))
		{
			IMovieDAO movieDAO = factory.makeMovieDAO();
			Movie movie = movieDAO.getMovieById(itemID);
			displayDetailed = detailedDisplaySetter.makeDetailedMovie(movie);
		}
		else if(itemType.equals(CategoryEnum.MUSIC.getText()))
		{
			IMusicDAO musicDAO = factory.makeMusicDAO();
			Music music = musicDAO.getMusicById(itemID);
			displayDetailed = detailedDisplaySetter.makeDetailedMusic(music);
		}
		return displayDetailed;
	}

}
