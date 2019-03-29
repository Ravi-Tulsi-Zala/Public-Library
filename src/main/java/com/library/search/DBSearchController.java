package com.library.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.signOut.ISignOutObserver;
import com.library.signOut.SignOutController;

public class DBSearchController implements IDBSearchController, ISignOutObserver {
	
	private Map<String, SearchRequestsAndResults> searchesPerSessionId = new HashMap<>();
	@Inject
	private ISearchResultCoverImgProxy coverImageProxy;
	
	public DBSearchController() {
		SignOutController.instance().registerAsSignOutObserver(this);
	}
		
	private class SearchRequestsAndResults {
		SearchRequest requestDetails;
		SearchResults results;
		
		public SearchRequestsAndResults(SearchRequest requestDetails, SearchResults results) {
			this.requestDetails = requestDetails;
			this.results = results;
		}
	}

	
	@Override
	public SearchResults search(SearchRequest requestDetails, HttpSession httpSession) {
		SearchRequestsAndResults searchRAndR = null;
		String sessionId = httpSession.getId();
		
		boolean isNotFirstSearchForSessionID = searchesPerSessionId.containsKey(httpSession.getId());
		boolean isSameSearchCriteriaButDifferentResultsPage = false;
		if(isNotFirstSearchForSessionID) {
			isSameSearchCriteriaButDifferentResultsPage = 
					searchesPerSessionId.get(sessionId).requestDetails.onlyRequestedPageDiffers(requestDetails);
		}
		
		if(isNotFirstSearchForSessionID) {
			if(isSameSearchCriteriaButDifferentResultsPage) {
				searchRAndR = searchesPerSessionId.get(httpSession.getId());
				searchRAndR.requestDetails.setRequestedResultsPageNumber(
						requestDetails.getRequestedResultsPageNumber());
			} else {
				coverImageProxy.deleteCoverImagesForSearchResults(httpSession);
				searchesPerSessionId.remove(httpSession.getId());
				searchRAndR = executeSearchInDb(requestDetails,httpSession);
			}
		} else {
			searchRAndR = executeSearchInDb(requestDetails, httpSession);
		}
		
		int requestedPageNumber = searchRAndR.requestDetails.getRequestedResultsPageNumber(); 
		SearchResults resultSet = searchRAndR.results.getResultSetForPageNumber(requestedPageNumber);
		if(resultSet.isNotEmpty()) {
			coverImageProxy.loadCoverImages(resultSet, Integer.toString(requestedPageNumber), httpSession);
		}
		return resultSet;
	}

	private SearchRequestsAndResults executeSearchInDb(SearchRequest requestDetails, HttpSession httpSession) {
		SearchResults searchResults = requestDetails.searchInDb();
		SearchRequestsAndResults searchRAndR = new SearchRequestsAndResults(requestDetails, searchResults);
		searchesPerSessionId.put(httpSession.getId(), searchRAndR);
		return searchRAndR;
	}

	@Override
	public void notifyUserSignOut(HttpSession httpSession) {
		String sessionId = httpSession.getId();
		if(searchesPerSessionId.containsKey(sessionId)) {
			searchesPerSessionId.remove(sessionId);
			coverImageProxy.deleteCoverImagesForSearchResults(httpSession);
		}
	}
}
