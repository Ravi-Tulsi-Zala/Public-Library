package com.library.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.business.itemSearch.SearchQuery;
import com.library.business.itemSearch.SearchResult;
import com.library.model.DataBaseBean;
import com.library.model.IDataBase;
import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@ComponentScan(basePackages = {"com.library.model"},
basePackageClasses = DataBaseBean.class)
@Controller
public class LibraryController extends HttpServlet implements WebMvcConfigurer {

	@Autowired
	private IDataBase dataBase;
	
	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			User user) {

		IUserExtendedInfo userExtendedInfo = new UserExtendedInfo();
		IUserBasicInfo userBasicInfo = new UserBasicInfo();
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPwd(user.getPassword());
		userExtendedInfo.setCPassword(user.getCpassword());
		userExtendedInfo.setFullname(user.getFullName());
		userExtendedInfo.setPhone(user.getPhoneNumber());

		List<Map.Entry<String, String>> list = SignUpController.insertInDBIfAuthenticate(userBasicInfo,
				userExtendedInfo, request);
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
		}
		// model object has by default two values; anytime it gets more than that
		// signifies a validation violation
		if (model.size() > 2) {
			return "SignUpForm";
		}
		return "Results";
	}

	@GetMapping("/signUp")
	public String getSignUpForm(User user) {
		return "SignUpForm";
	}
	
	@GetMapping("/advancedSearch")
	public String getAdvancedSearchPage(ModelMap model) {
		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setExtendedSearch(true);
		model.addAttribute("searchQuery", searchQuery);
		return "AdvancedSearchPage.html";
	}
	

	@PostMapping("/search")
	public String getSearchResults(ModelMap model, SearchQuery searchQuery) 
	{		
		SearchResult searchResults = dataBase.search(searchQuery);
		model.addAttribute("searchResults", searchResults);
		
		return "SearchResultsPage.html";	
	}	

}
