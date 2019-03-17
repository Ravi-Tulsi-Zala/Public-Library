package com.library.browsePage;

import java.util.List;

import com.library.businessModels.Display;

public interface IDisplayObjects {
	public List<Display> makeDisplayItem(String category);
	public List<String> getCategories();
}
