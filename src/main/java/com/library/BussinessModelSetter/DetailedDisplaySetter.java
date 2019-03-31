package com.library.BussinessModelSetter;
import com.library.IBussinessModelSetter.IDetailedDisplaySetter;
import com.library.IBussinessModelSetter.IDetailsSetter;
import com.library.businessModels.Book;
import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class DetailedDisplaySetter implements IDetailedDisplaySetter{

	private IDetailsSetter detailsSetter;
	private DisplayDetailed displayDetailed;
	
	public DetailedDisplaySetter() {
		detailsSetter = new DetailsSetter();
		displayDetailed = new DisplayDetailed();
	}
	
	@Override
	public DisplayDetailed makeDetailedBook(Book book) {
		displayDetailed.setItemID(book.getItemID());
		displayDetailed.setTitle(book.getTitle());
		String details = detailsSetter.getBookDetails(book);
		displayDetailed.setDetails(details);
		return displayDetailed;
	}

	@Override
	public DisplayDetailed makeDetailedMovie(Movie movie) {
		displayDetailed.setItemID(movie.getItemID());
		displayDetailed.setTitle(movie.getTitle());
		String details = detailsSetter.getMovieDetails(movie);
		displayDetailed.setDetails(details);
		return displayDetailed;
	}

	@Override
	public DisplayDetailed makeDetailedMusic(Music music) {
		displayDetailed.setItemID(music.getItemID());
		displayDetailed.setTitle(music.getTitle());
		String details = detailsSetter.getMusicDetails(music);
		displayDetailed.setDetails(details);
		return displayDetailed;
	}

}
