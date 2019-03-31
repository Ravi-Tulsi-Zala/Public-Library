package com.library.BussinessModelSetter;

import com.library.IBussinessModelSetter.IDetailsSetter;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class DetailsSetter implements IDetailsSetter {
	
	public String getBookDetails(Book book)
	{
		String details = "";
		details = details + "Author: " + book.getAuthor() + "\n";
		details = details + "Publisher: " + book.getPublisher() + "\n";
		details = details + book.getDescription();
		return details;
	}
	
	public String getMovieDetails(Movie movie)
	{
		String details = "";
		details = details + "Director: " + movie.getDirector() + "\n";
		details = details + movie.getDescription() + "\n";
		return details;
	}
	
	public String getMusicDetails(Music music)
	{
		String details = "";
		details = details + "Artist: " + music.getArtist() + "\n";
		details = details + "Record Label: " + music.getRecordLabel() + "\n";
		return details;
	}
	
}

