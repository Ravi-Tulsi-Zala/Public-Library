package com.library.DAOFactory;

import com.library.DAO.BookDAO;
import com.library.DAO.CoverDAO;
import com.library.DAO.IBookDAO;
import com.library.DAO.ICoverDAO;
import com.library.DAO.ILibraryItemDAO;
import com.library.DAO.IMovieDAO;
import com.library.DAO.IMusicDAO;
import com.library.DAO.IUserDAO;
import com.library.DAO.IUserItemDAO;
import com.library.DAO.LibraryItemDAO;
import com.library.DAO.MovieDAO;
import com.library.DAO.MusicDAO;
import com.library.DAO.UserDAO;
import com.library.DAO.UserItemDAO;


public class DAOFactory implements IDAOFactory {

	@Override
	public IBookDAO makeBookDAO() {

		return new BookDAO();
	}

	@Override
	public ICoverDAO makeCoverDAO() {

		return new CoverDAO();
	}

	@Override
	public ILibraryItemDAO makeLibraryItemDAO() {

		return new LibraryItemDAO();
	}

	@Override
	public IMovieDAO makeMovieDAO() {

		return new MovieDAO();
	}

	@Override
	public IMusicDAO makeMusicDAO() {

		return new MusicDAO();
	}

	@Override
	public IUserDAO makeUserDAO() {

		return new UserDAO();
	}

	@Override
	public IUserItemDAO makeUserItemDAO() {

		return new UserItemDAO();
	}

}
