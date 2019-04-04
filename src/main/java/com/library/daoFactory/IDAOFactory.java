package com.library.DAOFactory;

import com.library.DAO.IBookDAO;
import com.library.DAO.ICoverDAO;
import com.library.DAO.ILibraryItemDAO;
import com.library.DAO.IMovieDAO;
import com.library.DAO.IMusicDAO;
import com.library.DAO.IUserDAO;
import com.library.DAO.IUserItemDAO;

public interface IDAOFactory {

	public IBookDAO makeBookDAO();

	public ICoverDAO makeCoverDAO();

	public ILibraryItemDAO makeLibraryItemDAO();

	public IMovieDAO makeMovieDAO();

	public IMusicDAO makeMusicDAO();

	public IUserDAO makeUserDAO();

	public IUserItemDAO makeUserItemDAO();

}
