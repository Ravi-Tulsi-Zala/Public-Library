package com.library.search;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class MockSearchRequest extends SearchRequest {
	private SearchResults searchResults = SearchFactory.instance().makeSearchResults();
	
	public MockSearchRequest() {
		List<LibraryItem> notUsedHere = new LinkedList<LibraryItem>();
		MockNumOfItemsInResult mockNumOfItemsInResult = new MockNumOfItemsInResult(15, 17, 19);
		SearchResultsPopulator.populateSearchResults(notUsedHere , searchResults, mockNumOfItemsInResult);
	}

	@Override
	public ISearchResults searchInDb() {
		return searchResults;
	}
}
