package com.library.business.itemSearch;

import java.util.List;

public class SearchResult {
	private List<ItemDescriptor> bookSearchResults = null;
	private List<ItemDescriptor> musicSearchResults = null;
	private List<ItemDescriptor> movieSearchResults = null;
	
	public List<ItemDescriptor> getBookSearchResults() {
		return bookSearchResults;
	}
	public void setBookSearchResults(List<ItemDescriptor> bookSearchResults) {
		this.bookSearchResults = bookSearchResults;
	}
	public List<ItemDescriptor> getMusicSearchResults() {
		return musicSearchResults;
	}
	public void setMusicSearchResults(List<ItemDescriptor> musicSearchResults) {
		this.musicSearchResults = musicSearchResults;
	}
	public List<ItemDescriptor> getMovieSearchResults() {
		return movieSearchResults;
	}
	public void setMovieSearchResults(List<ItemDescriptor> movieSearchResults) {
		this.movieSearchResults = movieSearchResults;
	}

}
