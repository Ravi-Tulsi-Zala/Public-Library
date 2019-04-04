package com.library.search;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;

public class MockBookSearch extends BookSearch {
	private List<LibraryItem> books = new LinkedList<>();
	public MockBookSearch(int numOfItems) {
		for(int i = 0; i < numOfItems; ++i) {
			books.add(new Book());
		}
	}
	@Override
	public List<LibraryItem> search(String searchterms) {
		return books;
	}
}
