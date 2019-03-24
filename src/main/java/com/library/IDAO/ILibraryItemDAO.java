package com.library.IDAO;

import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public interface ILibraryItemDAO {
	public List<Book> getTopBooks();
	public List<Movie> getTopMovies();
	public List<Music> getTopMusic();
}
