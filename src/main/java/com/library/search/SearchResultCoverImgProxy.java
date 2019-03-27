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
	private HashMap<String, LinkedList<String>> sessionIdToRequestedPageResults = 
															new HashMap<String, LinkedList<String>>();

	@Override
	public void loadCoverImages(SearchResults searchResults, String requestedPageNumber, HttpSession httpSession) {
		
		String sessionResultsPath = "searchResults" + SEPARATOR + httpSession.getId() + SEPARATOR;
		
		List<LibraryItem> items = searchResults.getAllFoundItems();
		
		LinkedList<String> resultPagesForSession = sessionIdToRequestedPageResults.get(httpSession.getId());
		if(null == resultPagesForSession) {
			resultPagesForSession = new LinkedList<String>();
			sessionIdToRequestedPageResults.put(httpSession.getId(), resultPagesForSession);
		} else {
			for(String previouslyRequestedPageNumber : resultPagesForSession) {
				if(requestedPageNumber.equals(previouslyRequestedPageNumber)) {;
					return;
				}
			}
		}
		resultPagesForSession.add(requestedPageNumber);
		
		String urlForRequestedPageNumber = sessionResultsPath + requestedPageNumber;
		
		for(LibraryItem item : items) {
			String imageUrl = imageLoader.loadCoverImageByItemIdToDisk(item.getItemID(), urlForRequestedPageNumber);
			item.setCoverImageUrl(imageUrl);
		}
	}

	@Override
	public void deleteCoverImagesForSearchResults(HttpSession httpSession) {
		String sessionResultsDir = "searchResults" + SEPARATOR + httpSession.getId() + SEPARATOR;
		sessionIdToRequestedPageResults.remove(httpSession.getId());
		imageLoader.deleteDynamicContent(sessionResultsDir);	
	}
}
