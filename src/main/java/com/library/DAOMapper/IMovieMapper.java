package com.library.DAOMapper;

import java.sql.ResultSet;

import com.library.BusinessModels.Movie;

public interface IMovieMapper {
	public Movie mapBook(ResultSet resultSet);
}
