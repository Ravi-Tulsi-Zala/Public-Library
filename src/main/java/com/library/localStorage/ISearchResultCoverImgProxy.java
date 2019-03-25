package com.library.localStorage;

import javax.servlet.http.HttpSession;

import com.library.search.SearchResults;

public interface ISearchResultCoverImgProxy {
	void deleteCoverImagesForSearchResults(HttpSession httpSession);
	void loadCoverImages(SearchResults searchResults, String requestedPageNumber, HttpSession httpSession);
}
