package com.library.model;

import com.library.itemSearch.SearchRequestDetails;
import com.library.itemSearch.SearchResult;

public interface IItemsDB {
	public SearchResult search(SearchRequestDetails searchRequestDetails);
}
