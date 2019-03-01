package com.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.business.itemSearch.SearchQuery;
import com.library.business.itemSearch.SearchResult;
import com.library.model.DataBaseBean;
import com.library.model.IDataBase;



@ComponentScan(basePackages = {"com.library.model"},
			   basePackageClasses = DataBaseBean.class)
@Controller
public class libraryItems_demo {
	
	@Autowired
	private IDataBase dataBase;
	
	@GetMapping("/")
	String entry() {
		return "Home.jsp";
	}
	
	@GetMapping("/signUp")
	public String responseBody() {
		return "signUp.jsp";
	}	
	
	@PostMapping("/post")
	public @ResponseBody ResponseEntity<String> post() {
	    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
	}
	
	@GetMapping("/getAdvancedSearchPage")
	public String getAdvancedSearchPage(Model model) {
		model.addAttribute("searchSelections", new SearchQuery());
		return "AdvancedSearchPage.html";
	}
	

	@PostMapping ("/search")
	public String getAdvancedSearchResults(@ModelAttribute/*("searchQuery")*/ SearchQuery searchQuery,
										   ModelMap model) 
	{		
		SearchResult searchResults = dataBase.search(searchQuery);
		
		searchResults.setSearchResultsPageNumber(searchQuery.getSearchResultsPageNumber());
		model.addAttribute("searchResults", searchResults);
		
		return "SearchResults.jsp";
	}
}
