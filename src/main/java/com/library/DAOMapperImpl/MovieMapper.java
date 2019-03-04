package com.library.DAOMapperImpl;

import java.sql.ResultSet;

import com.library.BusinessModels.Movie;
import com.library.DAOMapper.IMovieMapper;

public class MovieMapper implements IMovieMapper{

	
	@Override
	public Movie mapBook(ResultSet resultSet){
		Movie movie = new Movie();
		try
		{
			movie.setTitle(resultSet.getString("Title"));
			movie.setCategory(resultSet.getString("Category"));
			movie.setDescription(resultSet.getString("Description"));
			movie.setDirector(resultSet.getString("Director"));
			movie.setAvailability(resultSet.getInt("Availability"));
			return movie;
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;	
	}

}
