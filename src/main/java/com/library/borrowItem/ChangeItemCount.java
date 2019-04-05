package com.library.borrowItem;

import com.library.dao.IBookDAO;
import com.library.dao.IMovieDAO;
import com.library.dao.IMusicDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;

public class ChangeItemCount {

	private static String book = "Book";
	private static String music = "Music";
	private static String movie = "Movie";
	private int itemID;
	private String itemType;
	IDAOFactory factory;

	public ChangeItemCount(String itemType, int itemID) {
		this.itemID = itemID;
		this.itemType = itemType;
		factory = new DAOFactory();
	}

	private Boolean changeBookCount() {
		Boolean countChanged;
		IBookDAO bookDAO = factory.makeBookDAO();
		countChanged = bookDAO.increaseCount(itemID);
		return countChanged;
	}

	private Boolean changeMovieCount() {
		Boolean countChanged;
		IMovieDAO movieDAO = factory.makeMovieDAO();
		countChanged = movieDAO.increaseCount(itemID);
		return countChanged;
	}

	private Boolean changeMusicCount() {
		Boolean countChanged;
		IMusicDAO musicDAO = factory.makeMusicDAO();
		countChanged = musicDAO.increaseCount(itemID);
		return countChanged;
	}

	public Boolean changeCount() {
		Boolean isCountChanged = false;
		if (itemType.equals(book)) {
			isCountChanged = changeBookCount();
		} else if (itemType.equals(movie)) {
			isCountChanged = changeMovieCount();
		} else if (itemType.equals(music)) {
			isCountChanged = changeMusicCount();
		}
		return isCountChanged;
	}
}
