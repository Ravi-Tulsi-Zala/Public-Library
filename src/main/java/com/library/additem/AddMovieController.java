package com.library.additem;

import org.springframework.web.multipart.MultipartFile;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Movie;
import com.library.controllers.LibraryFactorySingleton;

public class AddMovieController implements IAddMovieController {

	IDAOFactory factory;
	int itemIdOfMovie;
	IMovieDAO iMovieDAO;
	boolean isMovieCreated, isMovieCoverCreated;

	public AddMovieController() {

		factory = new DAOFactory();
		iMovieDAO = factory.makeMovieDAO();
	}

	public boolean addMovieRecordInDatabase(Movie movie, MultipartFile movieCoverImage) {

		itemIdOfMovie = iMovieDAO.createMovie(movie);
		if (itemIdOfMovie == 0) {
			isMovieCreated = false;
		} else {
			isMovieCoverCreated = LibraryFactorySingleton.instance().getFactory().makeItemCoverSetter()
					.isCoverAddedToDatabase(itemIdOfMovie, movieCoverImage);
			isMovieCreated = true;

		}
		return (isMovieCreated && isMovieCoverCreated);
	}

}
