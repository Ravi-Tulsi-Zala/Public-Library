package com.library.business.itemSearch;

public class SearchQuery {
	
	private String searchTerms = null;
	private String searchResultsPageNumber = null;
	
	private boolean isExtendedSearch = false;
	
	private boolean searchInBooks = false;
	private boolean searchBookTitle = false;
	private boolean searchBookAuthor = false;
	private boolean searchBookPublisher = false;
	private boolean searchBookDescription = false;
	private boolean searchBookISBN = false;
	
	private boolean searchInMusic = false;
	private boolean searchMusicAlbumName = false;
	private boolean searchMusicArtist = false;
	private boolean searchMusicRecordLabel = false;
	
	private boolean searchInMovie = false;
	private boolean searchMovieTitle = false;
	private boolean searchMovieDirector = false;
	private boolean searchMovieDescription = false;
	
	public boolean isExtendedSearch() {
		return isExtendedSearch;
	}
	public void setIsExtendedSearch(boolean isExtendedSearch) {
		this.isExtendedSearch = isExtendedSearch;
	}
	
	public String getSearchTerms() {
		return searchTerms;
	}
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	public String getSearchResultsPageNumber() {
		return searchResultsPageNumber;
	}
	public void setSearchResultsPageNumber(String searchResultsPageNumber) {
		this.searchResultsPageNumber = searchResultsPageNumber;
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
	public boolean isSearchInMovie() {
		return searchInMovie;
	}
	public void setSearchInMovie(boolean searchInMovie) {
		this.searchInMovie = searchInMovie;
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
