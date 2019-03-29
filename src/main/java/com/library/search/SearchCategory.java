package com.library.search;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.LibraryItem;

abstract class SearchCategory {
	protected DAOFactory daoFactory = new DAOFactory();
	public abstract List<LibraryItem> search(String searchterms);
	public abstract boolean equals(SearchCategory searchCategory);
}
