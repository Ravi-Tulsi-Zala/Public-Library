package com.library.DAOMapper;

import java.sql.ResultSet;

import com.library.businessModels.Movie;

public interface IMovieMapper {
	public Movie mapMovie(ResultSet resultSet);
}
