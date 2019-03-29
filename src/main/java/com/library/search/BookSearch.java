package com.library.search;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.LibraryItem;

public class BookSearch implements ISearchCategory {
	private boolean searchInBooks = true;
	private boolean searchBookTitle = true;
	private boolean searchBookAuthor = true;
	private boolean searchBookCategory = true;
	private boolean searchBookPublisher = true;
	private boolean searchBookDescription = true;
	private boolean searchBookISBN = true;
	private DAOFactory daoFactory = new DAOFactory();
	
	@Override
	public List<LibraryItem> search(String searchterms) {
		return daoFactory.makeBookDAO().getBooksBySearchTerms(this, searchterms);
	}
	
	public boolean equals(ISearchCategory previousBookSearch) {
		BookSearch prev = (BookSearch) previousBookSearch;
		boolean isEqual = 
				this.searchInBooks == prev.searchInBooks &&
				this.searchBookAuthor == prev.searchBookAuthor &&
				this.searchBookPublisher == prev.searchBookPublisher &&
				this.searchBookDescription == prev.searchBookDescription &&
				this.searchBookISBN == prev.searchBookISBN ;
		
		return isEqual;
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

	public boolean isSearchBookCategory() {
		return searchBookCategory;
	}

	public void setSearchBookCategory(boolean searchBookCategory) {
		this.searchBookCategory = searchBookCategory;
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
}