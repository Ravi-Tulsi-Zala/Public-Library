package com.library.search;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import com.library.businessModels.LibraryItem;
import com.library.localStorage.ICoverImageLoader;
import com.library.signOut.ISignOutObserver;

public class SearchResultCoverImgProxy implements ISearchResultCoverImgProxy {
	
	private final String separator = File.separator;
	private final String searchResultsPath = separator +"dynamicContent" + separator +"searchResults" + separator;
	private static final Logger logger = LogManager.getLogger(SearchResultCoverImgProxy.class);
	
	@Inject
	private ICoverImageLoader imageLoader;
	private HashMap<String, LinkedList<String>> sessionIdToRequestedPageResults = 
															new HashMap<String, LinkedList<String>>();

	@Override
	public void loadCoverImages(SearchResults searchResults, String requestedPageNumber, HttpSession httpSession) {
		
		String sessionResultsDir = searchResultsPath + httpSession.getId() + separator;
		
		List<LibraryItem> items = new LinkedList<>(); // memory leak?
		items.addAll(searchResults.getBooks());
		items.addAll(searchResults.getMusic());
		items.addAll(searchResults.getMovies());
		
		LinkedList<String> resultPagesForSession = sessionIdToRequestedPageResults.get(httpSession.getId());
		if(null == resultPagesForSession) {
			resultPagesForSession = new LinkedList<String>();
			sessionIdToRequestedPageResults.put(httpSession.getId(), resultPagesForSession);
		} else {
			for(String previouslyRequestedPageNumber : resultPagesForSession) {
				if(requestedPageNumber.equals(previouslyRequestedPageNumber));
				return;
			}
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
