package com.library.search;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.library.DAO.BookDAO;
import com.library.DAO.MovieDAO;
import com.library.DAO.MusicDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.signOut.ISignOutObserver;
import com.library.signOut.SignOutController;

public class DBSearchController implements IDBSearchController, ISignOutObserver {
		
	private final int DESPLAY_ROW_SIZE = 10; // should move to the configuration file
	private Map<String, SearchRequestsAndResults> searchesPerSessionId = new HashMap<>();
	@Inject
	private ISearchResultCoverImgProxy coverImageProxy;
	
	public DBSearchController() {
		SignOutController.instance().registerAsSignOutObserver(this);
	}
		
	private class SearchRequestsAndResults {
		SearchRequestDetails requestDetails;
		SearchResults results;
		
		public SearchRequestsAndResults(SearchRequestDetails requestDetails, SearchResults results) {
			this.requestDetails = requestDetails;
			this.results = results;
		}
	}

	
	@Override
	public SearchResults search(SearchRequestDetails requestDetails, HttpSession httpSession) {
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
		
		SearchResults resultSet = getResultSetForRequestedPageNumber(searchRAndR);
		if(resultSet.isNotEmpty()) {
			int requestedPageNumber = searchRAndR.requestDetails.getRequestedResultsPageNumber();
			coverImageProxy.loadCoverImages(resultSet, Integer.toString(requestedPageNumber), httpSession);
		}
		return resultSet;
	}

	private SearchRequestsAndResults executeSearchInDb(SearchRequestDetails searchRequestDetails, HttpSession httpSession) {
		SearchResults searchResults = searchInDb(searchRequestDetails);
		SearchRequestsAndResults searchRAndR = new SearchRequestsAndResults(searchRequestDetails, searchResults);
		searchesPerSessionId.put(httpSession.getId(), searchRAndR);
		return searchRAndR;
	}

	private SearchResults searchInDb(SearchRequestDetails searchRequestDetails) {
		SearchResults searchResults = new SearchResults();
		if(searchRequestDetails.isExtendedSearch()) {
			if(searchRequestDetails.isSearchInBooks()) {
				searchResults.setBooks(new BookDAO().getBooksBySearchTerms(searchRequestDetails));
			}
			if(searchRequestDetails.isSearchInMusic()) {
				searchResults.setMusic(new MusicDAO().getMusicBySearchTerms(searchRequestDetails));
			}
			if(searchRequestDetails.isSearchInMovies()) {
				searchResults.setMovies(new MovieDAO().getMoviesBySearchTerms(searchRequestDetails));
			}
		} else {
			searchResults.setBooks(new BookDAO().getBooksBySearchTerms(searchRequestDetails));
			searchResults.setMusic(new MusicDAO().getMusicBySearchTerms(searchRequestDetails));
			searchResults.setMovies(new MovieDAO().getMoviesBySearchTerms(searchRequestDetails));
		}
		return searchResults;
	}
	
	protected SearchResults getResultSetForRequestedPageNumber(SearchRequestsAndResults searchRAndR) {
		int requestedPageNumber = searchRAndR.requestDetails.getRequestedResultsPageNumber();
		SearchResults results = new SearchResults();
		
		if(null != searchRAndR.results.getBooks()) {
			Iterator<Book> iterator = searchRAndR.results.getBooks().iterator();
			for(int i=0; i < (requestedPageNumber -1)*DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				iterator.next();
			}
			for(int i=0; i < DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				results.getBooks().add(iterator.next()); 
			}
		}
		
		if(null != searchRAndR.results.getMusic()) {
			Iterator<Music> iterator = searchRAndR.results.getMusic().iterator();
			for(int i=0; i < (requestedPageNumber -1)*DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				iterator.next();
			}
			for(int i=0; i < DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				results.getMusic().add(iterator.next()); 
			}
		}
		
		if(null != searchRAndR.results.getMovies()) {
			Iterator<Movie> iterator = searchRAndR.results.getMovies().iterator();
			for(int i=0; i < (requestedPageNumber -1)*DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				iterator.next();
			}
			for(int i=0; i < DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				results.getMovies().add(iterator.next()); 
			}
		}
				
		return results;
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
