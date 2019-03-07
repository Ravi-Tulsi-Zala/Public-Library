package com.library.controllers;

import java.util.List;

import java.util.Map;
import javax.inject.Inject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.business.itemSearch.SearchQuery;
import com.library.business.itemSearch.SearchResult;
import com.library.model.DataBaseBean;
import com.library.model.IDataBase;
import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;
import com.library.singIn.SignInController;

@ComponentScan(basePackages = {"com.library.model"},
basePackageClasses = DataBaseBean.class)
@Controller
public class LibraryController implements WebMvcConfigurer {

	@Inject
	private IDataBase dataBase;
	
	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model,User user) {

		IUserExtendedInfo userExtendedInfo = new UserExtendedInfo();
		IUserBasicInfo userBasicInfo = new UserBasicInfo();
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPwd(user.getPassword());
		userExtendedInfo.setCPassword(user.getCpassword());
		userExtendedInfo.setFullname(user.getFullName());
		userExtendedInfo.setPhone(user.getPhoneNumber());

		List<Map.Entry<String, String>> list = new SignUpController(userBasicInfo, userExtendedInfo).authenticateSignUp();
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

//	@RequestMapping("/")
//	String entry() {
//		return "Home.jsp";
//	} 	

	@GetMapping("/signIn")
	public String responseBody(User user) {
		return "SignInForm";
	}

	@PostMapping("/signIn")
	public String process(ModelMap model,User user) {

		IUserBasicInfo userBasicInfo = new UserBasicInfo();
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPwd(user.getPassword());
		List<Map.Entry<String, String>> list =  new SignInController(userBasicInfo).authenticateSignIn();
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
		}
		// model object has by default two values; anytime it gets more than that
		// signifies a validation violation
		if (model.size() > 2) {
			return "SignInForm";
		}
		return "Results";
	}

}
