package com.library.model;

import com.library.business.itemSearch.SearchQuery;
import com.library.business.itemSearch.SearchResult;

public interface IItemsDB {
	public SearchResult search(SearchQuery searchRequestDetails);
}
