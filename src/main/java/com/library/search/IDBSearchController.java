package com.library.search;

import javax.servlet.http.HttpSession;

public interface IDBSearchController {
	public SearchResults search(AdvancedSearchRequest searchRequestDetails, HttpSession httpSession);
}
