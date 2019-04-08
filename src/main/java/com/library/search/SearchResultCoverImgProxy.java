package com.library.search;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.library.businessModels.LibraryItem;
import com.library.localStorage.ICoverImageLoader;

public class SearchResultCoverImgProxy implements ISearchResultCoverImgProxy {
	
	private static final String SEPARATOR = File.separator;
	
	@Inject
	private ICoverImageLoader imageLoader;
	private HashMap<HttpSession, LinkedList<String>> sessionToListOfRequestedPages = 
															new HashMap<HttpSession, LinkedList<String>>();
	
	@Override
	public void loadCoverImages(SearchResults resultsForRequestedPage, String requestedPageNumber, HttpSession httpSession) {
		String sessionResultsPath = "searchResults" + SEPARATOR + httpSession.getId() + SEPARATOR;
		String pathToRequestedPageNumberImagesDir = sessionResultsPath + requestedPageNumber;

		LinkedList<String> resultPagesForSession = sessionToListOfRequestedPages.get(httpSession);
		if(null == resultPagesForSession) {
			resultPagesForSession = new LinkedList<String>();
			sessionToListOfRequestedPages.put(httpSession, resultPagesForSession);
		} else {
			for(String previouslyRequestedPageNumber : resultPagesForSession) {
				if(requestedPageNumber.equals(previouslyRequestedPageNumber)) {;
					return;
				}
			}
		}
		resultPagesForSession.add(requestedPageNumber);
		
		List<LibraryItem> items = resultsForRequestedPage.getAllFoundItems();
		for(LibraryItem item : items) {
			String imageUrl = imageLoader.loadCoverImageByItemIdToDisk(item.getItemID(), pathToRequestedPageNumberImagesDir);
			item.setCoverImageUrl(imageUrl);
		}
	}

	@Override
	public void deleteCoverImagesForSearchResults(HttpSession httpSession) {
		String sessionResultsDir = "searchResults" + SEPARATOR + httpSession.getId() + SEPARATOR;
		sessionToListOfRequestedPages.remove(httpSession);
		imageLoader.deleteDynamicContent(sessionResultsDir);	
	}
}
