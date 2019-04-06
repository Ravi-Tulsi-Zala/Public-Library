package com.library.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.library.businessModels.Book;
import com.library.businessModels.BusinessModelsFactory;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class SearchResultsTest {
	private SearchResults sr = null;
	List<LibraryItem> addedItems = null;
	private static BusinessModelsFactory bmf = BusinessModelsFactory.instance();

	@Before
	public void setUp() throws Exception {
		sr = SearchFactory.instance().makeSearchResults();
		addedItems = new LinkedList<>();;
	}

	@Test
	public void isNotEmptyPassesWhenOneBookIsAdded() {
		List<LibraryItem> books = new LinkedList<LibraryItem>();
		books.add(bmf.makeBook());
		sr.addSearchResultsForCategory(books);
		assertTrue(sr.isNotEmpty());
	}	
	@Test
	public void isNotEmptyPassesWhenOneMovieIsAdded() {
		List<LibraryItem> movies = new LinkedList<LibraryItem>();
		movies.add(bmf.makeMovie());
		sr.addSearchResultsForCategory(movies);
		assertTrue(sr.isNotEmpty());
	}
	@Test
	public void isNotEmptyPassesWhenOneMusicIsAdded() {
		List<LibraryItem> musics = new LinkedList<LibraryItem>();
		musics.add(bmf.makeMusic());
		sr.addSearchResultsForCategory(musics);
		assertTrue(sr.isNotEmpty());
	}
	@Test
	public void canGetAllFoundItems() {
		SearchresultsPopulator.populateSearchResults(addedItems, sr);		
		List<LibraryItem> items = sr.getAllFoundItems();
		items.containsAll(addedItems);
		assertTrue(items.size() == addedItems.size());
	}	
	@Test
	public void canGetSearchResultsPerCategory() {
		SearchresultsPopulator.populateSearchResults(addedItems, sr);
		ArrayList<List<LibraryItem>> resultsPerCategories = sr.getSearchResultsPerCategory() ;
		for(List<LibraryItem> resultsPerCategory : resultsPerCategories) {
			addedItems.containsAll(resultsPerCategory);
			int size = resultsPerCategory.size();
			assertTrue(size == 15 || size == 17 || size == 19);
		}		
	}
	@Test
	public void canGetResultSetForPageNumber() {
		SearchresultsPopulator.populateSearchResults(addedItems, sr);
		SearchResults sr = this.sr.getResultSetForPageNumber(1);
		ArrayList<List<LibraryItem>> resultsPerCategories = sr.getSearchResultsPerCategory();
		for(List<LibraryItem> resultsPerCategory : resultsPerCategories) {
			addedItems.containsAll(resultsPerCategory);
			assertTrue(resultsPerCategory.size() == 10);
		}
		sr = this.sr.getResultSetForPageNumber(2);
		resultsPerCategories = sr.getSearchResultsPerCategory();
		assertTrue(resultsPerCategories.size() == 3);
		assertTrue(resultsPerCategories.get(0).size() == 5);
		assertTrue(resultsPerCategories.get(1).size() == 7);
		assertTrue(resultsPerCategories.get(2).size() == 9);
	}
}
