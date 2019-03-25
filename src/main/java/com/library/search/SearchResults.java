package com.library.search;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class SearchResults {
	private LinkedList<Book> books = new LinkedList<Book>();
	private LinkedList<Music> music = new LinkedList<Music>();
	private LinkedList<Movie> movies = new LinkedList<Movie>();

	public LinkedList<Book> getBooks() {
		return books;
	}
	public void setBooks(LinkedList<Book> books) {
		this.books = books;
	}
	public LinkedList<Music> getMusic() {
		return music;
	}
	public void setMusic(LinkedList<Music> music) {
		this.music = music;
	}
	public LinkedList<Movie> getMovies() {
		return movies;
	}
	public void setMovies(LinkedList<Movie> movies) {
		this.movies = movies;
	}
	public boolean isNotEmpty() {
		boolean resultSetIsNotEmpty = !(books.isEmpty() && music.isEmpty() & movies.isEmpty());
		return resultSetIsNotEmpty;
	}
}
