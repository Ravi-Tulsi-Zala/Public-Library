package com.library.daoFactory;

import com.library.dao.BookDAO;
import com.library.dao.CoverDAO;
import com.library.dao.IBookDAO;
import com.library.dao.ICoverDAO;
import com.library.dao.ILibraryItemDAO;
import com.library.dao.IMovieDAO;
import com.library.dao.IMusicDAO;
import com.library.dao.IUserDAO;
import com.library.dao.IUserItemDAO;
import com.library.dao.LibraryItemDAO;
import com.library.dao.MovieDAO;
import com.library.dao.MusicDAO;
import com.library.dao.UserDAO;
import com.library.dao.UserItemDAO;

import com.library.dao.*;

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
