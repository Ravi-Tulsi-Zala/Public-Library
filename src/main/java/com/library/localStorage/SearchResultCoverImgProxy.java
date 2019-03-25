package com.library.localStorage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import com.library.businessModels.LibraryItem;
import com.library.search.SearchResults;

public class SearchResultCoverImgProxy implements ISearchResultCoverImgProxy{
	
	private final String separator = File.separator;
	private final String searchResultsPath = separator +"dynamicContent" + separator +"searchResults" + separator;
	
	@Inject
	private ICoverImageLoader imageLoader;
	private HashMap<String, HashMap<String, LinkedList<String>>> sessionIdToRequestedPageResults = 
															new HashMap<String, HashMap<String, LinkedList<String>>>();

	@Override
	public void loadCoverImages(SearchResults searchResults, String requestedPageNumber, HttpSession httpSession) {
		
		String sessionResultsDir = searchResultsPath + httpSession.getId() + separator;
		
		List<LibraryItem> items = new LinkedList<>();
		items.addAll(searchResults.getBookSearchResults());
		items.addAll(searchResults.getMusicSearchResults());
		items.addAll(searchResults.getMovieSearchResults());
		
		if(items.isEmpty()) {
			return;
		}
		
		HashMap<String, LinkedList<String>> resultsForSession = sessionIdToRequestedPageResults.get(httpSession.getId());
		if(null == resultsForSession) {
			resultsForSession = new HashMap<String, LinkedList<String>>();
			sessionIdToRequestedPageResults.put(httpSession.getId(), resultsForSession);
		} else {
			if(resultsForSession.containsKey(requestedPageNumber)) {
				return;
			}
		}
		
		if(!resultsForSession.containsKey(requestedPageNumber)) {
			resultsForSession.put(requestedPageNumber, new LinkedList<String>());
		}
		
		String urlForRequestedPageNumber = sessionResultsDir + requestedPageNumber;
		String basePath = System.getProperty("user.dir") + urlForRequestedPageNumber;
		
		File dir = new File(basePath);
		if (!dir.exists()) {
			dir.mkdirs();
		} else {
			// log that directory exists, but it should not.
			return;
		}
		
		for(LibraryItem item : items) {
			String imageName = imageLoader.loadCoverImageByItemIdToDisk(item.getItemID(), basePath);
			
			if(null != imageName) {
				item.setCoverImageUrl(urlForRequestedPageNumber + separator + imageName);
				resultsForSession.get(requestedPageNumber).add(urlForRequestedPageNumber + separator + imageName);
			}
		}
	}

	@Override
	public void deleteCoverImagesForSearchResults(HttpSession httpSession) {
		String sessionResultsDir = searchResultsPath + httpSession.getId() + separator;
		try {
			sessionIdToRequestedPageResults.remove(httpSession.getId());
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + sessionResultsDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
