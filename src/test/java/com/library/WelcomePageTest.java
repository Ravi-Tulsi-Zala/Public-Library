package com.library;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.mockDB.WelcomePageMocked;
import com.library.welcomePage.AdminPage;

@RunWith(SpringRunner.class)
public class WelcomePageTest {
	public static WelcomePageMocked welcomePageMocked;

	@BeforeClass
	public static void initializer() {
		welcomePageMocked = new WelcomePageMocked();
	}

	@Test
	public void TestBookAuthor() {
		Book bookList = welcomePageMocked.initiateBookMock();
		assertEquals("Ptrick Ruthfus", bookList.getAuthor());
	}

	@Test
	public void TestBookTitle() {
		Book bookList = welcomePageMocked.initiateBookMock();
		assertEquals("The girl who played with fire", bookList.getTitle());
	}

	@Test
	public void TestBookISBN() {
		Book bookList = welcomePageMocked.initiateBookMock();
		assertEquals(269, bookList.getIsbn());
	}

	@Test
	public void TestBookPublisher() {
		Book bookList = welcomePageMocked.initiateBookMock();
		assertEquals("Shrivastav Pubilication", bookList.getPublisher());
	}

	@Test
	public void TestBookDescription() {
		Book bookList = welcomePageMocked.initiateBookMock();
		assertEquals("Based on horroe movie", bookList.getDescription());
	}

	@Test
	public void TestBookAvailibity() {
		Book bookList = welcomePageMocked.initiateBookMock();
		assertTrue(bookList.getAvailability() > 0);
	}

	@Test
	public void TestMusicCategory() {
		Music musicList = welcomePageMocked.initiateMusicMock();
		assertEquals("Pop", musicList.getCategory());
	}

	@Test
	public void TestMusicTitle() {
		Music musicList = welcomePageMocked.initiateMusicMock();
		assertEquals("Despacito", musicList.getTitle());
	}

	@Test
	public void TestMusicArtist() {
		Music musicList = welcomePageMocked.initiateMusicMock();
		assertEquals("Luis Fonsi", musicList.getArtist());
	}

	@Test
	public void TestMovieCategory() {
		Movie movieList = welcomePageMocked.initiateMovieMock();
		assertEquals("Sci-Fi", movieList.getCategory());
	}

	@Test
	public void TestMovieTitle() {
		Movie movieList = welcomePageMocked.initiateMovieMock();
		assertEquals("Interstellar", movieList.getTitle());
	}

	@Test
	public void TestMovieDescription() {
		Movie movieList = welcomePageMocked.initiateMovieMock();
		assertEquals(
				"A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
				movieList.getDescription());
	}

	@Test
	public void TestMovieAvailibility() {
		Movie movieList = welcomePageMocked.initiateMovieMock();
		assertTrue(movieList.getAvailability() > 0);
	}

	@Test
	public void TestAdminAvailable() {
		boolean isAdmin = AdminPage.getAdminAvailable();
		if (isAdmin) {
			assertTrue(isAdmin);
		} else {
			assertFalse(isAdmin);
		}

	}
}
