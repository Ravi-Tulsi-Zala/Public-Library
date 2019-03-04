package com.library.IDAO;

import java.util.List;

public interface ILibraryItemDAO {
	public List<Integer> getRecentlyAdded();
	public List<Integer> getMostPopular();
	public List<Integer> getItemFromKeyword(String keyword);
}
