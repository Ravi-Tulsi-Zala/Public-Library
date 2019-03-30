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
		ISearchRequest requestDetails;
		ISearchResults results;
		
		public SearchRequestAndResults(ISearchRequest requestDetails, ISearchResults results) {
			this.requestDetails = requestDetails;
			this.results = results;
		}
	}

	@Override
	public SearchResults search(ISearchRequest requestDetails, HttpSession httpSession) {
		SearchRequestAndResults searchRAndR = null;
		String sessionId = httpSession.getId();
		
		boolean searchIsInProgress = sessionIdToSearchRAndR.containsKey(sessionId);
		boolean isNewSearchTerms = false;
		
		if(searchIsInProgress) {
			ISearchRequest previousSearchRequest = sessionIdToSearchRAndR.get(sessionId).requestDetails;
			isNewSearchTerms = previousSearchRequest.isNewSearchTerms(requestDetails);
			if(isNewSearchTerms) {
				clearPreviousSearch(httpSession);
				previousSearchRequest.getTermsAndPage().setRequestedResultsPageNumber(FIRST_PAGE);
				String newSearchTerms = requestDetails.getTermsAndPage().getSearchTerms();
				previousSearchRequest.getTermsAndPage().setSearchTerms(newSearchTerms);
				searchRAndR = executeSearchInDb(previousSearchRequest,httpSession);
			} else {
				searchRAndR = sessionIdToSearchRAndR.get(sessionId);
				int pageNumber = requestDetails.getTermsAndPage().getRequestedResultsPageNumber();
				searchRAndR.requestDetails.getTermsAndPage().setRequestedResultsPageNumber(pageNumber);
			}
		} else {
			searchRAndR = executeSearchInDb(requestDetails, httpSession);
		}
		
		int requestedPageNumber = searchRAndR.requestDetails.getTermsAndPage().getRequestedResultsPageNumber(); 
		SearchResults resultSet = searchRAndR.results.getResultSetForPageNumber(requestedPageNumber);
		if(resultSet.isNotEmpty()) {
			coverImageProxy.loadCoverImages(resultSet, Integer.toString(requestedPageNumber), httpSession);
		}
		return resultSet;
	}

	private SearchRequestAndResults executeSearchInDb(ISearchRequest requestDetails, HttpSession httpSession) {
		ISearchResults searchResults = requestDetails.searchInDb();
		SearchRequestAndResults searchRAndR = new SearchRequestAndResults(requestDetails, searchResults);
		sessionIdToSearchRAndR.put(httpSession.getId(), searchRAndR);
		return searchRAndR;
	}

	@Override
	public void notifyUserSignOut(HttpSession httpSession) {
		String sessionId = httpSession.getId();
		if(sessionIdToSearchRAndR.containsKey(sessionId)) {
			clearPreviousSearch(httpSession);
		}
	}
	
	@Override
	public void clearPreviousSearch(HttpSession httpSession) {
		coverImageProxy.deleteCoverImagesForSearchResults(httpSession);
		sessionIdToSearchRAndR.remove(httpSession.getId());
	}
}
