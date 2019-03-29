package com.library.search;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.LibraryItem;

public class MoviesSearch implements ISearchCategory {

	private boolean searchInMovies = true;
	private boolean searchMovieTitle = true;
	private boolean searchMovieDirector = true;
	private boolean searchMovieDescription = true;
	private DAOFactory daoFactory = new DAOFactory();
	
	@Override
	public List<LibraryItem> search(String searchterms) {
		return daoFactory.makeMovieDAO().getMoviesBySearchTerms(this, searchterms);
	}

	public boolean isSearchInMovies() {
		return searchInMovies;
	}

	public void setSearchInMovies(boolean searchInMovies) {
		this.searchInMovies = searchInMovies;
	}

	public boolean isSearchMovieTitle() {
		return searchMovieTitle;
	}

	public void setSearchMovieTitle(boolean searchMovieTitle) {
		this.searchMovieTitle = searchMovieTitle;
	}

	public boolean isSearchMovieDirector() {
		return searchMovieDirector;
	}

	public void setSearchMovieDirector(boolean searchMovieDirector) {
		this.searchMovieDirector = searchMovieDirector;
	}

	public boolean isSearchMovieDescription() {
		return searchMovieDescription;
	}

	public void setSearchMovieDescription(boolean searchMovieDescription) {
		this.searchMovieDescription = searchMovieDescription;
	}

	@Override
	public boolean equals(ISearchCategory previousMovieSearch) {
		MoviesSearch prev = (MoviesSearch) previousMovieSearch;
		boolean isEqual = 
				this.searchInMovies == prev.searchInMovies &&
				this.searchMovieTitle == prev.searchMovieTitle &&
				this.searchMovieDirector == prev.searchMovieDirector &&
				this.searchMovieDescription == prev.searchMovieDescription;
		
		return isEqual;
	}

}
