package com.library.search;

import javax.servlet.http.HttpSession;

public interface IDBSearchController {
	public SearchResults search(SearchRequest searchRequestDetails, HttpSession httpSession);
}
