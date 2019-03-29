package com.library.search;

import java.util.List;
import com.library.businessModels.LibraryItem;

public interface ISearchCategory {
	public List<LibraryItem> search(String searchterms);
	public boolean equals(ISearchCategory iCategorySearch);
}
