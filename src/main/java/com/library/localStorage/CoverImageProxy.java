package com.library.localStorage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.library.DAO.CoverDAO;
import com.library.businessModels.Cover;
import com.library.businessModels.LibraryItem;
import com.library.search.SearchResults;

public class CoverImageProxy {
	
	final String separator = File.separator;
	
	private HashMap<Integer, List<String>> itemIdToCoverUrListForMainPage = new HashMap<Integer, List<String>>();
	private HashMap<String, HashMap<String, LinkedList<String>>> sessionIdToRequestedPageResults = 
															new HashMap<String, HashMap<String, LinkedList<String>>>();
	
	private CoverImageProxy() {}
	private static CoverImageProxy coverImageProxy = null;
	
	public static CoverImageProxy instance() {
		if(null == coverImageProxy) {
			coverImageProxy = new CoverImageProxy();
		}
		return coverImageProxy;
	}

	public void loadCoverImages(SearchResults searchResults, int requestedPageNumber, HttpSession httpSession) {
		
		String requestedPageNumberStr = Integer.toString(requestedPageNumber);
		HashMap<String, LinkedList<String>> resultsForSession = sessionIdToRequestedPageResults.get(httpSession.getId());
		if(null == resultsForSession) {
			resultsForSession = new HashMap<String, LinkedList<String>>();
			sessionIdToRequestedPageResults.put(httpSession.getId(), resultsForSession);
		} else {
			if(resultsForSession.containsKey(requestedPageNumberStr)) {
				return;
			}
		}
		
		List<LibraryItem> items = new LinkedList<>();
		items.addAll(searchResults.getBookSearchResults());
		items.addAll(searchResults.getMusicSearchResults());
		items.addAll(searchResults.getMovieSearchResults());
		
		String baseUrl = System.getProperty("user.dir") + separator + "searchResults" + separator + httpSession.getId() + 
																		separator + requestedPageNumberStr;
		
		CoverDAO coverDao = new CoverDAO();
		byte [] bytes;
		
		if(!resultsForSession.containsKey(requestedPageNumberStr)) {
			resultsForSession.put(requestedPageNumberStr, new LinkedList<String>());
		}
		
		// source: https://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files
		File dir = new File(baseUrl);
		if (!dir.exists())
			dir.mkdirs();
		
		for(LibraryItem item : items) {
			Cover cover = coverDao.getCoverByID(item.getItemID());
			if(null != cover) {
				try {
					bytes = cover.getCoverBlob().getBytes(1, (int) cover.getCoverBlob().length());
				
					String url = baseUrl + separator + item.getItemID() + "." + cover.getFileExtension();
					resultsForSession.get(requestedPageNumberStr).add(url);
					File file = new File(url);
				
					BufferedOutputStream stream;
					stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
					item.setCoverImageUrl(url);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteCoverImages(HttpSession httpSession) {
		try {
			sessionIdToRequestedPageResults.remove(httpSession.getId());
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + separator + "searchResults" + separator + httpSession.getId()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
//		List<String> listOfUrls = sessionIdToRequestedPageResults.get(httpSession.getId());
//		Path path = null;
//		
//		for(String url : listOfUrls) {
//			path = Paths.get(url);
//			try {
//			    Files.delete(path);
//			} catch (NoSuchFileException x) {
//			    //logger.log(String.format("%s: no such" + " file or directory%n", path));
//			} catch (DirectoryNotEmptyException x) {
//			  //logger.log(String.format("%s not empty%n", path));
//			} catch (IOException x) {
//			    // File permission problems are caught here.
//				//logger.log(x.getMessage());
//			}
//		}
//		
//		path = Paths.get(System.getProperty("user.dir") + File.separator + httpSession.getId());
//		try {
//		    Files.delete(path);
//		} catch (NoSuchFileException x) {
//		    //logger.log(String.format("%s: no such" + " file or directory%n", path));
//		} catch (DirectoryNotEmptyException x) {
//		  //logger.log(String.format("%s not empty%n", path));
//		} catch (IOException x) {
//		    // File permission problems are caught here.
//			//logger.log(x.getMessage());
//		}
//		sessionIdToRequestedPageResults.remove(httpSession.getId());		
	}
}
