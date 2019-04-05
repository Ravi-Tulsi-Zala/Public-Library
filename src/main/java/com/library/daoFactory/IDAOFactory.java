package com.library.daoFactory;

import com.library.dao.IBookDAO;
import com.library.dao.ICoverDAO;
import com.library.dao.ILibraryItemDAO;
import com.library.dao.IMovieDAO;
import com.library.dao.IMusicDAO;
import com.library.dao.IUserDAO;
import com.library.dao.IUserItemDAO;

public interface IDAOFactory {

	public IBookDAO makeBookDAO();

	public ICoverDAO makeCoverDAO();

	public ILibraryItemDAO makeLibraryItemDAO();

	public IMovieDAO makeMovieDAO();

	public IMusicDAO makeMusicDAO();

	public IUserDAO makeUserDAO();

	public IUserItemDAO makeUserItemDAO();

}
