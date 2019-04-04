package com.library.search;

import java.util.List;

import com.library.businessModels.LibraryItem;
import com.library.daoFactory.DAOFactory;

abstract class SearchCategory {
	protected DAOFactory daoFactory = new DAOFactory();
	public abstract List<LibraryItem> search(String searchterms);
}
