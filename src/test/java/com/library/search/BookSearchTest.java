package com.library.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookSearchTest {
	private BookSearch bs1;
	private BookSearch bs2;

	@Before
	public void setUp() throws Exception {
		bs1 = new BookSearch();
		bs2 = new BookSearch();
	}
	
	@Test
	public void allFieldsAreSame() {
		assertTrue(bs1.equals(bs2));
	}	
	@Test
	public void isSearchBookAuthorDiffers() {
		bs1.setSearchBookAuthor(false);
		bs2.setSearchBookAuthor(true);
		assertFalse(bs1.equals(bs2));
	}	
	@Test
	public void isSearchBookCategoryDiffers() {
		bs1.setSearchBookCategory(false);
		bs2.setSearchBookCategory(true);
		assertFalse(bs1.equals(bs2));
	}
	@Test
	public void isSearchBookDescriptionDiffers() {
		bs1.setSearchBookDescription(false);
		bs2.setSearchBookDescription(true);
		assertFalse(bs1.equals(bs2));
	}
	@Test
	public void isSearchBookISBNDiffers() {
		bs1.setSearchBookISBN(false);
		bs2.setSearchBookISBN(true);
		assertFalse(bs1.equals(bs2));
	}
	@Test
	public void isSearchBookPublisherDiffers() {
		bs1.setSearchBookPublisher(false);
		bs2.setSearchBookPublisher(true);
		assertFalse(bs1.equals(bs2));
	}
	@Test
	public void isSearchBookTitleDiffers() {
		bs1.setSearchBookTitle(false);
		bs2.setSearchBookTitle(true);
		assertFalse(bs1.equals(bs2));
	}
	@Test
	public void isSearchInBooksDiffers() {
		bs1.setSearchInBooks(false);
		bs2.setSearchInBooks(true);
		assertFalse(bs1.equals(bs2));
	}
	@Test
	public void canSetAndGetSearchBookAuthor() {
		assertTrue(bs1.isSearchBookAuthor());
		bs1.setSearchBookAuthor(false);
		assertFalse(bs1.isSearchBookAuthor());
	}
	@Test
	public void canSetAndGetSearchBookCategory() {
		assertTrue(bs1.isSearchBookCategory());
		bs1.setSearchBookCategory(false);
		assertFalse(bs1.isSearchBookCategory());
	}
	@Test
	public void canSetAndGetSearchBookDescription() {
		assertTrue(bs1.isSearchBookDescription());
		bs1.setSearchBookDescription(false);
		assertFalse(bs1.isSearchBookDescription());
	}
	@Test
	public void canSetAndGetSearchBookISBN() {
		assertTrue(bs1.isSearchBookISBN());
		bs1.setSearchBookISBN(false);
		assertFalse(bs1.isSearchBookISBN());
	}
	@Test
	public void canSetAndGetSearchBookPublisher() {
		assertTrue(bs1.isSearchBookPublisher());
		bs1.setSearchBookPublisher(false);
		assertFalse(bs1.isSearchBookPublisher());
	}
	@Test
	public void canSetAndGetSearchBookTitle() {
		assertTrue(bs1.isSearchBookTitle());
		bs1.setSearchBookTitle(false);
		assertFalse(bs1.isSearchBookTitle());
	}
	@Test
	public void canSetAndGetSearchInBooks() {
		assertTrue(bs1.isSearchInBooks());
		bs1.setSearchInBooks(false);
		assertFalse(bs1.isSearchInBooks());
	}
}
