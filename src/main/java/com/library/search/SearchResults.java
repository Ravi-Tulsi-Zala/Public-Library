package com.library.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.LibraryItem;

public class SearchResults {
	private final int DESPLAY_ROW_SIZE = 10; // should move to the configuration file
	private ArrayList<List<LibraryItem>> searchResultsPerCategory = new ArrayList<List<LibraryItem>>();

	public boolean isNotEmpty() {
		for(List<LibraryItem> categoryResult : searchResultsPerCategory) {
			if(!categoryResult.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public List<LibraryItem> getAllFoundItems() {
		List<LibraryItem> allItems = new LinkedList<LibraryItem>();
		for(List<LibraryItem> categoryResult : searchResultsPerCategory) {
			allItems.addAll(categoryResult);
		}
		return allItems;
	}
	

	
	public void addSearchResultsForCategory(List<LibraryItem> resultsPerCategory) {
		searchResultsPerCategory.add(resultsPerCategory);
	}

	public SearchResults getResultSetForPageNumber(int requestedPageNumber) {
		SearchResults results = new SearchResults();
		for(List<LibraryItem> categoryResult : searchResultsPerCategory) {
			List<LibraryItem> resultsForCurrentCategory = new LinkedList<LibraryItem>();
			Iterator<LibraryItem> iterator = categoryResult.iterator();
			for(int i=0; i < (requestedPageNumber -1)*DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				iterator.next();
			}
			for(int i=0; i < DESPLAY_ROW_SIZE && iterator.hasNext(); i++) {
				resultsForCurrentCategory.add(iterator.next()); 
			}
			results.addSearchResultsForCategory(resultsForCurrentCategory);
		}
		return results;
	}
	
	public ArrayList<List<LibraryItem>> getSearchResultsPerCategory() {
		return searchResultsPerCategory;
	}
}
