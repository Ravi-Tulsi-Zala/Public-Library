package com.library.DAOMapper;

import java.sql.ResultSet;
import java.util.List;

import com.library.BusinessModels.Book;

public interface IBookMapper {
	public Book mapBook(ResultSet resultSet);
	List<Integer> getItemIDFromBook(List<Book> books);
}
