package com.library.ItemDetails;

import com.library.businessModels.Display;

public class DisplayDetails implements IDisplayDetails{

	@Override
	public void getDisplayDetails(Display display) {
		String itemType = display.getItemType();
		if(itemType=="Book")
		{
			
		}
		else if(itemType=="Music")
		{
			
		}
		else if(itemType=="Movie")
		{
			
		}
	}

}
