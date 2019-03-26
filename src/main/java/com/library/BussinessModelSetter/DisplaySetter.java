package com.library.BussinessModelSetter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IBussinessModelSetter.IDisplaySetter;
import com.library.IDAO.ICoverDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Cover;
import com.library.businessModels.Display;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.mysql.jdbc.Blob;

public class DisplaySetter implements IDisplaySetter{

	List<Display> displayObjects;;
	IDAOFactory factory = new DAOFactory();
	ICoverDAO coverDAO = factory.makeCoverDAO();
	
	@Override
	public List<Display> getBookDisplayObjects(List<Book> books) {
		Iterator<Book> bookIterator = books.iterator();
		displayObjects = new ArrayList<Display>();
		while(bookIterator.hasNext())
		{
			Display display = new Display();
			Book book = bookIterator.next();
			display.setTitle(book.getTitle());
			display.setItemID(book.getItemID());
			display.setItemType("Book");
			Cover cover =  coverDAO.getCoverByID(display.getItemID());
			display.setImage((Blob) cover.getCoverBlob());
			displayObjects.add(display);
		}
		return displayObjects;
	}

	@Override
	public List<Display> getMovieDisplayObjects(List<Movie> movies) {
		Iterator<Movie> movieIterator = movies.iterator();
		displayObjects = new ArrayList<Display>();
		while(movieIterator.hasNext())
		{
			Display display = new Display();
			Movie movie = movieIterator.next();
			display.setTitle(movie.getTitle());
			display.setItemID(movie.getItemID());
			display.setItemType("Movie");
			Cover cover = coverDAO.getCoverByID(display.getItemID());
			if(cover!=null)
			{
				display.setImage((Blob) cover.getCoverBlob());
			}
			displayObjects.add(display);
		}
		return displayObjects;
	}

	@Override
	public List<Display> getMusicDisplayObjects(List<Music> musics) {
		Iterator<Music> musicIterator = musics.iterator();
		displayObjects = new ArrayList<Display>();
		while(musicIterator.hasNext())
		{
			Display display = new Display();
			Music music = musicIterator.next();
			display.setTitle(music.getTitle());
			display.setItemID(music.getItemID());
			display.setItemType("Music");
			Cover cover = coverDAO.getCoverByID(display.getItemID());
			if(cover!=null)
			{
				display.setImage((Blob) cover.getCoverBlob());
			}
			displayObjects.add(display);
		}
		return displayObjects;
	}
}
