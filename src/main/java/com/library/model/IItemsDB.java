package com.library.model;

import com.library.itemSearch.SearchQuery;
import com.library.itemSearch.SearchResult;

public interface IItemsDB {
	public SearchResult search(SearchQuery searchRequestDetails);
}
