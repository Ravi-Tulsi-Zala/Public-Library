package com.library.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.browsePage.BrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayObjects;

@Controller	
public class BrowsePageController {

	private IBrowseDisplayObjects browseDisplayObjects;
	
	@GetMapping("/BrowsePage/{itemType}")
	public String BrowsePage(@PathVariable String itemType, ModelMap model) {
		
		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
		List<String> categories;
		if(itemType=="Book")
		{
			browseDisplayObjects = browseFactory.makeBookDisplay();
		}
		else if(itemType=="Music")
		{
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		}
		else if(itemType=="Movie")
		{
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		}
		categories = browseDisplayObjects.getCategories();
		model.addAttribute(categories);
		model.addAttribute(itemType);
		return "BrowsePage";
	}
}
