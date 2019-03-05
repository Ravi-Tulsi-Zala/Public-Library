package com.library.business.itemSearch;

public class SearchQuery {
	
	private String searchTerms = null;
	private int requestedSearchResultsPageNumber = 1;
	
	private boolean extendedSearch = false;
	
	private boolean searchInBooks = true;
	private boolean searchBookTitle = true;
	private boolean searchBookAuthor = true;
	private boolean searchBookPublisher = true;
	private boolean searchBookDescription = true;
	private boolean searchBookISBN = true;
	
	private boolean searchInMusic = true;
	private boolean searchMusicAlbumName = true;
	private boolean searchMusicArtist = true;
	private boolean searchMusicRecordLabel = true;
	
	private boolean searchInMovies = true;
	private boolean searchMovieTitle = true;
	private boolean searchMovieDirector = true;
	private boolean searchMovieDescription = true;
	
	public boolean isExtendedSearch() {
		return extendedSearch;
	}
	public void setExtendedSearch(boolean extendedSearch) {
		this.extendedSearch = extendedSearch;
	}
	
	public String getSearchTerms() {
		return searchTerms;
	}
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	public int getRequestedSearchResultsPageNumber() {
		return requestedSearchResultsPageNumber;
	}
	public void setRequestedSearchResultsPageNumber(int requestedSearchResultsPageNumber) {
		this.requestedSearchResultsPageNumber = requestedSearchResultsPageNumber;
	}
	public boolean isSearchInBooks() {
		return searchInBooks;
	}
	public void setSearchInBooks(boolean searchInBooks) {
		this.searchInBooks = searchInBooks;
	}
	public boolean isSearchBookTitle() {
		return searchBookTitle;
	}
	public void setSearchBookTitle(boolean searchBookTitle) {
		this.searchBookTitle = searchBookTitle;
	}
	public boolean isSearchBookAuthor() {
		return searchBookAuthor;
	}
	public void setSearchBookAuthor(boolean searchBookAuthor) {
		this.searchBookAuthor = searchBookAuthor;
	}
	public boolean isSearchBookPublisher() {
		return searchBookPublisher;
	}
	public void setSearchBookPublisher(boolean searchBookPublisher) {
		this.searchBookPublisher = searchBookPublisher;
	}
	public boolean isSearchBookDescription() {
		return searchBookDescription;
	}
	public void setSearchBookDescription(boolean searchBookDescription) {
		this.searchBookDescription = searchBookDescription;
	}
	public boolean isSearchBookISBN() {
		return searchBookISBN;
	}
	public void setSearchBookISBN(boolean searchBookISBN) {
		this.searchBookISBN = searchBookISBN;
	}
	public boolean isSearchInMusic() {
		return searchInMusic;
	}
	public void setSearchInMusic(boolean searchInMusic) {
		this.searchInMusic = searchInMusic;
	}
	public boolean isSearchMusicAlbumName() {
		return searchMusicAlbumName;
	}
	public void setSearchMusicAlbumName(boolean searchMusicAlbumName) {
		this.searchMusicAlbumName = searchMusicAlbumName;
	}
	public boolean isSearchMusicArtist() {
		return searchMusicArtist;
	}
	public void setSearchMusicArtist(boolean searchMusicArtist) {
		this.searchMusicArtist = searchMusicArtist;
	}
	public boolean isSearchMusicRecordLabel() {
		return searchMusicRecordLabel;
	}
	public void setSearchMusicRecordLabel(boolean searchMusicRecordLabel) {
		this.searchMusicRecordLabel = searchMusicRecordLabel;
	}
	public boolean isSearchInMovies() {
		return searchInMovies;
	}
	public void setSearchInMovies(boolean searchInMovie) {
		this.searchInMovies = searchInMovie;
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
	
	
	

}
