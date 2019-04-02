package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Movie;
import com.library.messages.Messages;

public interface IAddMovieController {

	public Messages addMovieRecordInDatabase(Movie movie, MultipartFile coverImage);
	
}
