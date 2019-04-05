package com.library.search;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.BusinessModelsFactory;
import com.library.businessModels.LibraryItem;

public class SearchresultsPopulator {
	private static BusinessModelsFactory bmf = BusinessModelsFactory.instance();
	public static void populateSearchResults(List<LibraryItem> addedItems, SearchResults sr) {
		List<LibraryItem> books = new LinkedList<LibraryItem>();
		for(int i = 0; i < 15; ++i) {
			LibraryItem book = bmf.makeBook();
			book.setAvailability(i);
			books.add(book);
		}
		sr.addSearchResultsForCategory(books);
		addedItems.addAll(books);
		
		List<LibraryItem> movies = new LinkedList<LibraryItem>();
		for(int i = 0; i < 17; ++i) {
			LibraryItem movie = bmf.makeMovie();
			movie.setAvailability(i);
			movies.add(movie);
		}
		sr.addSearchResultsForCategory(movies);
		addedItems.addAll(movies);
		
		List<LibraryItem> musics = new LinkedList<LibraryItem>();
		for(int i = 0; i < 19; ++i) {
			LibraryItem music = bmf.makeMusic();
			music.setAvailability(i);
			musics.add(music);
		}
		sr.addSearchResultsForCategory(musics);
		addedItems.addAll(musics);
	}
}
