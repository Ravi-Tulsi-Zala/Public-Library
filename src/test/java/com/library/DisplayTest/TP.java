package com.library.DisplayTest;

import java.util.List;

import org.junit.Test;

import com.library.browsePage.BrowseBooks;
import com.library.businessModels.Display;

public class TP {

	@Test
	public void checkDisplayImage()
	{	
		Display display = new Display();
		display.setItemID(100001);
	}
	
	@Test
	public void checkBookCategories()
	{
		BrowseBooks browseBooks = new BrowseBooks();
		List<String> categories = browseBooks.getCategories();
		System.out.print(categories.get(1));
	}
}
