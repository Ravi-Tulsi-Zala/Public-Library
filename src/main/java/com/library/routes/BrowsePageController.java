package com.library.routes;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.browsePage.BrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayObjects;
import com.library.businessModels.Display;

@Controller
public class BrowsePageController {

	
	private IBrowseDisplayObjects browseDisplayObjects;

	@GetMapping("/BrowsePage/{itemType}")
	public String BrowsePageCategory(@PathVariable String itemType, ModelMap model) {

		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
		List<String> categories;
		if (itemType.equals("Book")) {
			browseDisplayObjects = browseFactory.makeBookDisplay();
		} else if (itemType.equals("Movie")) {
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		} else if (itemType.equals("Music")) {
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		}
		categories = browseDisplayObjects.getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute(itemType);
		return "BrowsePageCategory";
	}
	
	@GetMapping("/BrowsePage/{itemType}/{category}")
	public String BrowsePageItems(@PathVariable(value="itemType") String itemType, @PathVariable(value="category") String category, ModelMap model)
	{
		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
		List<Display> displayItems;
		if (itemType.equals("Book")) {
			browseDisplayObjects = browseFactory.makeBookDisplay();
		} else if (itemType.equals("Movie")) {
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		} else if (itemType.equals("Music")) {
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		}
		
		displayItems = browseDisplayObjects.itemsByCategory(category);
		model.addAttribute("displayItems",displayItems);
		model.addAttribute(itemType);
		model.addAttribute(category);
		return "BrowsePageItems";
	}
}
