package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Movie;
import com.library.dao.IMovieDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
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

	public AddItemMessagesEnum addMovieRecordInDatabase(Movie movie, MultipartFile movieCoverImage) {

		isDuplicateMovie = iMovieDAO.checkMovieDuplicacy(movie);
		if (isDuplicateMovie) {
			return AddItemMessagesEnum.ERROR_DUPLICATE_MOVIE;
		}

		itemIdOfMovie = iMovieDAO.createMovie(movie);
		if (itemIdOfMovie == 0) {

			return AddItemMessagesEnum.ERROR_MOVIE_CAN_NOT_BE_CREATED;

		} else {
			coverSetter = iLibraryfactory.makeItemCoverSetter();
			isMovieCoverCreated = coverSetter.isCoverAddedToDatabase(itemIdOfMovie, movieCoverImage);
			if(isMovieCoverCreated)
			{
				return AddItemMessagesEnum.SUCCESS_MOVIE;
			}
			else
			{
				return AddItemMessagesEnum.ERROR_MOVIE_CAN_NOT_BE_CREATED;
			}

		}
	
	}

}
