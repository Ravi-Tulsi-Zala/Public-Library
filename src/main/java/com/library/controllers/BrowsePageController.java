package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.browsePage.BrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayObjects;
import com.library.businessModels.Display;

@Controller
public class BrowsePageController {
	
	
	private IBrowseDisplayObjects browseDisplayObjects;

	@Value("${itemType}")
	private String itemType;
	
	@Value("${category}")
	private String category;
	
	@RequestMapping("/BrowsePage/{ItemType}")
	public String BrowsePage(Model model, @PathVariable("ItemType") String itemType) {
		
		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
		if(itemType=="Book")
		{
			browseDisplayObjects = browseFactory.makeBookDisplay();
			itemType = "Book";
		}
		else if(itemType=="Music")
		{
			browseDisplayObjects = browseFactory.makeMusicDisplay();
			itemType = "Music";
		}
		else if(itemType=="Movie")
		{
			browseDisplayObjects = browseFactory.makeMusicDisplay();
			itemType = "Movie";
		}
		itemType = browseDisplayObjects.getItemType();
		return "BrowsePage";
	}
}



