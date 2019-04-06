package com.library.search;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.library.signOut.ISignOutObserver;
import com.library.signOut.SignOutController;

public class DBSearchController implements IDBSearchController, ISignOutObserver {
	
	private static final int FIRST_PAGE = 1;
	private Map<String, SearchRequestAndResults> sessionIdToSearchRAndR = new HashMap<>();
	@Inject
	private ISearchResultCoverImgProxy coverImageProxy;
	
	public DBSearchController() {
		SignOutController.instance().registerAsSignOutObserver(this);
	}
		
	private class SearchRequestAndResults {
		ISearchRequest searchRequest;
		ISearchResults results;
		
		public SearchRequestAndResults(ISearchRequest requestDetails, ISearchResults results) {
			this.searchRequest = requestDetails;
			this.results = results;
		}
	}

	@Override
	public SearchResults search(ISearchRequest currentRequest, HttpSession httpSession) {
		SearchRequestAndResults searchRAndR = null;
		String sessionId = httpSession.getId();
		
		boolean searchIsInProgress = sessionIdToSearchRAndR.containsKey(sessionId);
		boolean isNewSearchTerms = false;
		
		if(searchIsInProgress) {
			searchRAndR = sessionIdToSearchRAndR.get(sessionId);
			ISearchRequest request = searchRAndR.searchRequest;
			isNewSearchTerms = searchRAndR.searchRequest.isNewSearchTerms(currentRequest);
			if(isNewSearchTerms) {
				clearSearch(httpSession);
				SearchTermsAndPage prevTermsAndPage = request.getTermsAndPage();
				String newSearchTerms = currentRequest.getTermsAndPage().getSearchTerms();
				prevTermsAndPage.setRequestedResultsPageNumber(FIRST_PAGE);
				prevTermsAndPage.setSearchTerms(newSearchTerms);
				searchRAndR = executeSearchInDb(request,httpSession);
			} else {
				int pageNumber = currentRequest.getTermsAndPage().getRequestedResultsPageNumber();
				request.getTermsAndPage().setRequestedResultsPageNumber(pageNumber);
			}
		} else {
			searchRAndR = executeSearchInDb(currentRequest, httpSession);
		}
		
		int requestedPageNumber = searchRAndR.searchRequest.getTermsAndPage().getRequestedResultsPageNumber(); 
		SearchResults resultSet = searchRAndR.results.getResultSetForPageNumber(requestedPageNumber);
		if(resultSet.isNotEmpty()) {
			coverImageProxy.loadCoverImages(resultSet, Integer.toString(requestedPageNumber), httpSession);
		}
		return resultSet;
	}

	private SearchRequestAndResults executeSearchInDb(ISearchRequest request, HttpSession httpSession) {
		ISearchResults searchResults = request.searchInDb();
		SearchRequestAndResults searchRAndR = new SearchRequestAndResults(request, searchResults);
		sessionIdToSearchRAndR.put(httpSession.getId(), searchRAndR);
		return searchRAndR;
	}

	@Override
	public boolean notifyUserSignOut(HttpSession httpSession) {
		return clearSearch(httpSession);
	}
	
	@Override
	public boolean clearSearch(HttpSession httpSession) {
		if(sessionIdToSearchRAndR.containsKey(httpSession.getId())) {
			coverImageProxy.deleteCoverImagesForSearchResults(httpSession);
			sessionIdToSearchRAndR.remove(httpSession.getId());
		return true;
	}
	return false;
	}
}
