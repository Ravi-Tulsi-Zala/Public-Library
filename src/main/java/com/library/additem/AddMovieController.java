package com.library.additem;

import org.springframework.web.multipart.MultipartFile;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Movie;
import com.library.messages.Messages;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryFactorySingleton;

public class AddMovieController implements IAddMovieController {

	IDAOFactory factory;
	int itemIdOfMovie;
	IMovieDAO iMovieDAO;
	ILibraryFactory iLibraryfactory;
	LibraryFactorySingleton factorySingleton;
	IItemCoverSetter coverSetter;
	boolean isMovieCreated, isMovieCoverCreated, isDuplicateMovie;

	public AddMovieController() {

		factory = new DAOFactory();
		iMovieDAO = factory.makeMovieDAO();
		factorySingleton = LibraryFactorySingleton.instance();
		iLibraryfactory = factorySingleton.getFactory();

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
			coverSetter = iLibraryfactory.makeItemCoverSetter();
			isMovieCoverCreated = coverSetter.isCoverAddedToDatabase(itemIdOfMovie, movieCoverImage);
			if(isMovieCoverCreated)
			{
				return Messages.SUCCESS_MOVIE;
			}
			else
			{
				return Messages.ERROR_MOVIE_CAN_NOT_BE_CREATED;
			}

		}
	
	}

}
