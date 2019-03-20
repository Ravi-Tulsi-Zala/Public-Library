package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
//	private IBrowseDisplayObjects browseDisplayObjects;
//	
//	private String itemType = null;
//	
//	private String Category = null;
//	
	@GetMapping("/BrowsePage/{ItemType}")
	public String BrowsePage(Model model, @PathVariable("ItemType") String itemType) {
		
//		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
//		if(itemType=="Book")
//		{
//			browseDisplayObjects = browseFactory.makeBookDisplay();
//		}
//		else if(itemType=="Music")
//		{
//			browseDisplayObjects = browseFactory.makeMusicDisplay();
//		}
//		else if(itemType=="Movie")
//		{
//			browseDisplayObjects = browseFactory.makeMusicDisplay();
//		}
//		itemType = browseDisplayObjects.getItemType();
		return "BrowsePage";
	}
	
//	@ModelAttribute("BrowsePage/ItemType")
//	public String itemType()
//	{
//		return itemType;
//	}
//	
//	
//	@ModelAttribute("BrowsePage/items/{Category}")
//	public List<Display> items(@PathVariable("Category") String category)
//	{
//		Category = category;
//		return browseDisplayObjects.makeDisplayItem(category);
//	}
//	
//	@ModelAttribute("item/Categories")
//	public List<String> itemCategories()
//	{
//		return browseDisplayObjects.getCategories();
//	}
//	
//	@ModelAttribute("ItemType")
//	public String currentCategory()
//	{
//		return Category;
//	}
}



