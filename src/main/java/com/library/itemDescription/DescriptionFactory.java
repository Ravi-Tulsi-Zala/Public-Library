package com.library.itemDescription;

import com.library.businessModels.Display;

public class DescriptionFactory {

	Display display;
	IDescription description;
	
	public DescriptionFactory(Display display) {
		this.display = display;
		String itemType = display.getItemType();
		if(itemType == "Book")
		{
			description = new BookDescription(display);
		}
		else if(itemType == "Music")
		{
			description = new MusicDescription(display);
		}
		else if(itemType == "Movie")
		{
			description = new MovieDescription(display);
		}
		else
		{
			description = new BookDescription(display);
		}
	}
	
}
