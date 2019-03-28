package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Movie;

public interface IAddMovieController {

	public boolean addMovieRecordInDatabase(Movie movie, MultipartFile coverImage);
	
}
