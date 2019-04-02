package com.library.routes;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.borrowItem.ItemStatus;
import com.library.browsePage.DisplayObjectInitializer;
import com.library.browsePage.IBrowseDisplayObjects;
import com.library.businessModels.Display;
import com.library.businessModels.DisplayDetailed;
import com.library.itemDetailed.DetailedDisplayFetcher;
import com.library.itemDetailed.IDetailedDisplayFetcher;
import com.library.signIn.AuthenticatedUsers;
import com.library.welcomePage.AdminPage;

@Controller
public class BrowsePageController {

	
	
	@GetMapping("/itemDetail/{itemType}/{itemID}")
	public String BrowsePageItems1(@PathVariable(value = "itemType") String itemType,
			@PathVariable(value = "itemID") int itemID, ModelMap model, HttpSession httpSession) {
		IDetailedDisplayFetcher displayFetcher = new DetailedDisplayFetcher();
		DisplayDetailed displayDetailed = displayFetcher.fetchDetailedDisplay(itemType, itemID);
		AuthenticatedUsers user = AuthenticatedUsers.instance();
		String emailAddress = user.getUserEmail(httpSession);
		ItemStatus statusFetcher = new ItemStatus(displayDetailed,emailAddress);
		String status = statusFetcher.getItemStatus();
		String loggingStatus = AdminPage.getLoggingStatus();
		String sessionClient = AdminPage.getAvailableUserID();
		model.addAttribute("loggingStatus", loggingStatus);
		model.addAttribute("sessionClient", sessionClient);
		model.addAttribute("status",status);
		model.addAttribute("displayDetailed",displayDetailed);
		return "itemDetail";
	}
	
	
	

}
