package com.library.IDAO;

import java.sql.SQLException;
import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public interface ILibraryItemDAO {
	public List<Book> getTopBooks() throws SQLException;
	public List<Movie> getTopMovies() throws SQLException;
	public List<Music> getTopMusic() throws SQLException;
}
