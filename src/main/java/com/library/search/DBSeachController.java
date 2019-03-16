package com.library.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.library.DAO.BookDAO;
import com.library.DAO.MovieDAO;
import com.library.DAO.MusicDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
// This class is a Java bean --> singleton by default.
public class DBSeachController implements IDBSearchController {

	
	private final int NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW = 10; // should move to the configuration file
	private Map<String, SearchRequestsAndResults> sessionIdToSearchRequestsAndResults = new HashMap<>();
	
	protected class SearchRequestsAndResults {
		SearchRequestDetails searchRequestDetails;
		SearchResults searchResults;
		
		public SearchRequestsAndResults(SearchRequestDetails searchRequestDetails, SearchResults searchResults) {
			this.searchRequestDetails = searchRequestDetails;
			this.searchResults = searchResults;
		}
	}

	
	@Override
	synchronized public SearchResults search(SearchRequestDetails searchRequestDetails, HttpSession httpSession) {
		SearchRequestsAndResults searchRequestsAndResults = null;
		String sessionId = httpSession.getId();
		
		boolean isNotFirstSearchForSessionID = sessionIdToSearchRequestsAndResults.containsKey(httpSession.getId());
		boolean isSameSearechCriteriaButAnotherResultsPage = false;
		if(isNotFirstSearchForSessionID) {
			isSameSearechCriteriaButAnotherResultsPage = 
					sessionIdToSearchRequestsAndResults.get(sessionId).searchRequestDetails.onlyRequestedPageDiffers(searchRequestDetails);
		}
		
		if(isNotFirstSearchForSessionID) {
			if(isSameSearechCriteriaButAnotherResultsPage) {
				searchRequestsAndResults = sessionIdToSearchRequestsAndResults.get(httpSession.getId());
				searchRequestsAndResults.searchRequestDetails.setRequestedSearchResultsPageNumber(
						searchRequestDetails.getRequestedSearchResultsPageNumber());
			} else {
				sessionIdToSearchRequestsAndResults.remove(httpSession.getId());
				executeNewSearchInDb(searchRequestDetails,httpSession);
			}
		} else {
			searchRequestsAndResults = executeNewSearchInDb(searchRequestDetails, httpSession);
		}
		
		SearchResults resultSetForRequestedPageNum = calculateResultSetForRequestedSearchPageNumber(searchRequestsAndResults);
//		CoverImageController
//		loadCoverImages(resultSetForRequestedPageNum);
		return resultSetForRequestedPageNum;
	}

	protected SearchRequestsAndResults executeNewSearchInDb(SearchRequestDetails searchRequestDetails, HttpSession httpSession) {
		SearchResults searchResults = searchInDb(searchRequestDetails);
		SearchRequestsAndResults searchRequestsAndResults = new SearchRequestsAndResults(searchRequestDetails, searchResults);
		sessionIdToSearchRequestsAndResults.put(httpSession.getId(), searchRequestsAndResults);
		return searchRequestsAndResults;
	}

	protected SearchResults searchInDb(SearchRequestDetails searchRequestDetails) {
		SearchResults searchResults = new SearchResults();
		if(searchRequestDetails.isExtendedSearch()) {
			if(searchRequestDetails.isSearchInBooks()) {
				searchResults.setBookSearchResults(new BookDAO().getBooksBySearchTerms(searchRequestDetails));
			}
			if(searchRequestDetails.isSearchInMusic()) {
				searchResults.setMusicSearchResults(new MusicDAO().getMusicBySearchTerms(searchRequestDetails));
			}
			if(searchRequestDetails.isSearchInMovies()) {
				searchResults.setMovieSearchResults(new MovieDAO().getMoviesBySearchTerms(searchRequestDetails));
			}
		} else {
			searchResults.setBookSearchResults(new BookDAO().getBooksBySearchTerms(searchRequestDetails));
			searchResults.setMusicSearchResults(new MusicDAO().getMusicBySearchTerms(searchRequestDetails));
			searchResults.setMovieSearchResults(new MovieDAO().getMoviesBySearchTerms(searchRequestDetails));
		}
		return searchResults;
	}
	
	protected SearchResults calculateResultSetForRequestedSearchPageNumber(SearchRequestsAndResults searchRequestsAndResults) {
		int pageNumber = searchRequestsAndResults.searchRequestDetails.getRequestedSearchResultsPageNumber();
		SearchResults resultsForPageNumber = new SearchResults();
		
		if(null != searchRequestsAndResults.searchResults.getBookSearchResults()) {
			Iterator<Book> iterator = searchRequestsAndResults.searchResults.getBookSearchResults().iterator();
			for(int i=0; i < (pageNumber -1)*NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++, iterator.next()) {}
			for(int i=0; i < NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++) {
				resultsForPageNumber.getBookSearchResults().add(iterator.next()); 
			}
		}
		
		if(null != searchRequestsAndResults.searchResults.getMusicSearchResults()) {
			Iterator<Music> iterator = searchRequestsAndResults.searchResults.getMusicSearchResults().iterator();
			for(int i=0; i < (pageNumber -1)*NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++, iterator.next()) {}
			for(int i=0; i < NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++) {
				resultsForPageNumber.getMusicSearchResults().add(iterator.next()); 
			}
		}
		
		if(null != searchRequestsAndResults.searchResults.getMovieSearchResults()) {
			Iterator<Movie> iterator = searchRequestsAndResults.searchResults.getMovieSearchResults().iterator();
			for(int i=0; i < (pageNumber -1)*NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++, iterator.next()) {}
			for(int i=0; i < NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++) {
				resultsForPageNumber.getMovieSearchResults().add(iterator.next()); 
			}
		}
				
		return resultsForPageNumber;
	}
}
