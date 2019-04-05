package com.library.search;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;

public class MockMovieSearch extends MovieSearch{
	private List<LibraryItem> movies = new LinkedList<>();
	public MockMovieSearch(int numOfItems) {
		for(int i = 0; i < numOfItems; ++i) {
			movies.add(new Movie());
		}
	}
	@Override
	public List<LibraryItem> search(String searchterms) {
		return movies;
	}
}
