package com.library.browsePage;

import java.util.List;

import com.library.businessModels.Display;

public interface IBrowseDisplayComponent {
	public List<Display> itemsByCategory(String category);
	public List<String> getCategories();
}
