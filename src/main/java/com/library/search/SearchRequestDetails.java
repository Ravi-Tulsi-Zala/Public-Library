package com.library.search;


public class SearchRequestDetails implements IBookSearchRequestDetails, 
											 IMovieSearchRequestDetails, 
											 IMusicSearchRequestDetails {
	
	private String searchTerms = null;
	private int requestedResultsPageNumber = 1;
	
	private boolean extendedSearch = true;
	
	private boolean searchInBooks = true;
	private boolean searchBookTitle = true;
	private boolean searchBookAuthor = true;
	private boolean searchBookCategory = true;
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
	
	@Override
	public String getSearchTerms() {
		return searchTerms;
	}
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	public int getRequestedResultsPageNumber() {
		return requestedResultsPageNumber;
	}
	public void setRequestedResultsPageNumber(int requestedSearchResultsPageNumber) {
		this.requestedResultsPageNumber = requestedSearchResultsPageNumber;
	}
	public boolean isSearchInBooks() {
		return searchInBooks;
	}
	public void setSearchInBooks(boolean searchInBooks) {
		this.searchInBooks = searchInBooks;
	}
	@Override
	public boolean isSearchBookTitle() {
		return searchBookTitle;
	}
	public void setSearchBookTitle(boolean searchBookTitle) {
		this.searchBookTitle = searchBookTitle;
	}
	@Override
	public boolean isSearchBookAuthor() {
		return searchBookAuthor;
	}
	@Override
	public boolean isSearchBookCategory() {
		return searchBookCategory;
	}
	public void setSearchBookCategory(boolean searchBookCategory) {
		this.searchBookCategory = searchBookCategory;
	}
	public void setSearchBookAuthor(boolean searchBookAuthor) {
		this.searchBookAuthor = searchBookAuthor;
	}
	@Override
	public boolean isSearchBookPublisher() {
		return searchBookPublisher;
	}
	public void setSearchBookPublisher(boolean searchBookPublisher) {
		this.searchBookPublisher = searchBookPublisher;
	}
	@Override
	public boolean isSearchBookDescription() {
		return searchBookDescription;
	}
	public void setSearchBookDescription(boolean searchBookDescription) {
		this.searchBookDescription = searchBookDescription;
	}
	@Override
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
	@Override
	public boolean isSearchMusicAlbumName() {
		return searchMusicAlbumName;
	}
	public void setSearchMusicAlbumName(boolean searchMusicAlbumName) {
		this.searchMusicAlbumName = searchMusicAlbumName;
	}
	@Override
	public boolean isSearchMusicArtist() {
		return searchMusicArtist;
	}
	public void setSearchMusicArtist(boolean searchMusicArtist) {
		this.searchMusicArtist = searchMusicArtist;
	}
	@Override
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
	@Override
	public boolean isSearchMovieTitle() {
		return searchMovieTitle;
	}
	public void setSearchMovieTitle(boolean searchMovieTitle) {
		this.searchMovieTitle = searchMovieTitle;
	}
	@Override
	public boolean isSearchMovieDirector() {
		return searchMovieDirector;
	}
	public void setSearchMovieDirector(boolean searchMovieDirector) {
		this.searchMovieDirector = searchMovieDirector;
	}
	@Override
	public boolean isSearchMovieDescription() {
		return searchMovieDescription;
	}
	public void setSearchMovieDescription(boolean searchMovieDescription) {
		this.searchMovieDescription = searchMovieDescription;
	}
	
	public boolean onlyRequestedPageDiffers(SearchRequestDetails otherSearchRequestDetails) {
		if(this.searchTerms.equals(otherSearchRequestDetails.searchTerms) &&
			this.extendedSearch == otherSearchRequestDetails.extendedSearch &&
				
			this.searchInBooks == otherSearchRequestDetails.searchInBooks &&
			this.searchBookAuthor == otherSearchRequestDetails.searchBookAuthor &&
			this.searchBookPublisher == otherSearchRequestDetails.searchBookPublisher &&
			this.searchBookDescription == otherSearchRequestDetails.searchBookDescription &&
			this.searchBookISBN == otherSearchRequestDetails.searchBookISBN &&
				
			this.searchInMusic == otherSearchRequestDetails.searchInMusic &&
			this.searchMusicAlbumName == otherSearchRequestDetails.searchMusicAlbumName &&
			this.searchMusicArtist == otherSearchRequestDetails.searchMusicArtist &&
			this.searchMusicRecordLabel == otherSearchRequestDetails.searchMusicRecordLabel &&
				
			this.searchInMovies == otherSearchRequestDetails.searchInMovies &&
			this.searchMovieTitle == otherSearchRequestDetails.searchMovieTitle &&
			this.searchMovieDirector == otherSearchRequestDetails.searchMovieDirector &&
			this.searchMovieDescription == otherSearchRequestDetails.searchMovieDescription) {
			
			return true;
		}
		return false;
	}
}
