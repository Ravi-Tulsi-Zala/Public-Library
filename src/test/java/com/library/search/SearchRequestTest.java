package com.library.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class SearchRequestTest {
	private SearchRequest sr;

	@Before
	public void setUp() throws Exception {
		sr = new SearchRequest();
	}

	@Test
	public void canAddCategoriesToSearchInThenSearchAndReturnResults() {
		sr.addCategoryToSearchIn(new MockMusicSearch(12));
		sr.addCategoryToSearchIn(new MockMovieSearch(17));
		sr.addCategoryToSearchIn(new MockBookSearch(19));
		SearchTermsAndPage stap = new SearchTermsAndPage();
		stap.setSearchTerms("asd");
		sr.setTermsAndPage(stap);
		ISearchResults searchResults = sr.searchInDb();
		ArrayList<List<LibraryItem>> resultsPerCategory = searchResults.getSearchResultsPerCategory();
		assertTrue(resultsPerCategory.get(0).size() == 12);
		assertTrue(resultsPerCategory.get(1).size() == 17);
		assertTrue(resultsPerCategory.get(2).size() == 19);
		assertTrue(resultsPerCategory.get(0).get(0) instanceof Music);
		assertTrue(resultsPerCategory.get(1).get(0) instanceof Movie);
		assertTrue(resultsPerCategory.get(2).get(0) instanceof Book);
	}

}
