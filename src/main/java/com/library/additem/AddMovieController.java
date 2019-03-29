package com.library.additem;

import org.springframework.web.multipart.MultipartFile;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Movie;
import com.library.messages.Messages;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryControllerFactory;
import com.library.routes.LibraryFactorySingleton;

public class AddMovieController implements IAddMovieController {

	IDAOFactory factory;
	int itemIdOfMovie;
	IMovieDAO iMovieDAO;
	boolean isMovieCreated, isMovieCoverCreated, isDuplicateMovie;

	public AddMovieController() {

		factory = new DAOFactory();
		iMovieDAO = factory.makeMovieDAO();
		ILibraryFactory iLibraryfactory = new LibraryControllerFactory();
		LibraryFactorySingleton.instance().build(iLibraryfactory);
	}

	public Messages addMovieRecordInDatabase(Movie movie, MultipartFile movieCoverImage) {

		isDuplicateMovie = iMovieDAO.checkMovieDuplicacy(movie);
		if (isDuplicateMovie) {
			return Messages.ERROR_DUPLICATE_MOVIE;
		}

		itemIdOfMovie = iMovieDAO.createMovie(movie);
		if (itemIdOfMovie == 0) {

			return Messages.ERROR_MOVIE_CAN_NOT_BE_CREATED;

		} else {
			isMovieCoverCreated = LibraryFactorySingleton.instance().getFactory().makeItemCoverSetter()
					.isCoverAddedToDatabase(itemIdOfMovie, movieCoverImage);
			return Messages.SUCCESS_MOVIE;

		}
	
	}

}
