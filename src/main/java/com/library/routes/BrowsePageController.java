package com.library.routes;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.browsePage.BrowseDisplayFactory;
import com.library.browsePage.DisplayObjectInitializer;
import com.library.browsePage.IBrowseDisplayFactory;
import com.library.browsePage.IBrowseDisplayObjects;
import com.library.businessModels.Display;
import com.library.businessModels.DisplayDetailed;
import com.library.itemDetailed.DetailedDisplayFetcher;
import com.library.itemDetailed.IDetailedDisplayFetcher;

@Controller
public class BrowsePageController {

	
	@GetMapping("/BrowsePage/{itemType}")
	public String BrowsePageCategory(@PathVariable String itemType, ModelMap model) {
		DisplayObjectInitializer displayObjectInitializer = new DisplayObjectInitializer();
		IBrowseDisplayObjects browseDisplayObjects = null;
		List<String> categories;
		browseDisplayObjects = displayObjectInitializer.getDisplayObject(itemType);
		categories = browseDisplayObjects.getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("itemType", itemType);
		return "BrowsePageCategory";
	}

	@GetMapping("/BrowsePage/{itemType}/{category}")
	public String BrowsePageItems(@PathVariable(value = "itemType") String itemType,
			@PathVariable(value = "category") String category, ModelMap model) {
		
		DisplayObjectInitializer displayObjectInitializer = new DisplayObjectInitializer();
		IBrowseDisplayObjects browseDisplayObjects = null;
		List<Display> displayItems;
		browseDisplayObjects = displayObjectInitializer.getDisplayObject(itemType);
		displayItems = browseDisplayObjects.itemsByCategory(category);
		model.addAttribute("displayItems", displayItems);
		model.addAttribute(itemType);
		model.addAttribute(category);
		return "BrowsePageItems";
	}

	@GetMapping("/itemDetail/{itemType}/{itemID}")
	public String BrowsePageItems1(@PathVariable(value = "itemType") String itemType,
			@PathVariable(value = "itemID") int itemID, ModelMap model) {
		IDetailedDisplayFetcher displayFetcher = new DetailedDisplayFetcher();
		DisplayDetailed displayDetailed = displayFetcher.fetchDetailedDisplay(itemType, itemID);
		model.addAttribute("displayDetailed",displayDetailed);
		return "itemDetail";
	}

}
