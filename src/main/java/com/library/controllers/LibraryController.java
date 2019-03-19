package com.library.controllers;

import java.sql.Blob;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.signIn.AuthenticatedUsers;
import com.library.signIn.SignInController;
import com.library.DAO.CoverDAO;
import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.search.DBSeachControllerBean;
import com.library.search.IDBSearchController;
import com.library.search.SearchRequestDetails;
import com.library.search.SearchResults;
import com.library.signIn.AuthenticatedUsers;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@ComponentScan(basePackages = { "com.library.model" }, basePackageClasses = DBSeachControllerBean.class)
@Controller
public class LibraryController implements WebMvcConfigurer {
	private List<Map.Entry<String, String>> list = null;
	@Inject
	private IDBSearchController dbSearchController;

	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, User user) {
		try {
			ILibraryFactory factory = new LibraryControllerFactory();
			IUserExtendedInfo userExtendedInfo = new UserExtendedInfo();
			IUserBasicInfo userBasicInfo = new UserBasicInfo();
			userBasicInfo.setEmail(user.getEmail());
			userBasicInfo.setPwd(user.getPassword());
			userExtendedInfo.setCPassword(user.getCpassword());
			userExtendedInfo.setFullname(user.getFullName());
			userExtendedInfo.setPhone(user.getPhoneNumber());
			LibraryFactorySingleton.instance().build(factory);
			list= LibraryFactorySingleton.instance().getFactory()
					.signUp(userBasicInfo, userExtendedInfo).authenticateSignUp();
			for (int i = 0; i < list.size(); i++) {
				model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
			}
			// model object has by default two values; anytime it gets more than that
			// signifies a validation violation
			if (model.size() > 2) {
				return "SignUpForm";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Results";

	}

	@GetMapping("/signUp")
	public String getSignUpForm(User user) {
		return "SignUpForm";
	}

	@GetMapping("/advancedSearch")
	public String getAdvancedSearchPage(HttpSession httpSession, ModelMap model) {
		AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, "removeMeFromTheController@mail.com"); // remove
																												// once
																												// we
																												// will
																												// have
																												// users
																												// in
																												// the
																												// db
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			SearchRequestDetails searchRequestDetails = new SearchRequestDetails();
			searchRequestDetails.setExtendedSearch(true);
			model.addAttribute("searchRequestDetails", searchRequestDetails);
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));

			return "AdvancedSearchPage";
		}
		return "NoAccessToNonAuthenticated";
	}

	@PostMapping("/search")

	public String getSearchResults(HttpSession httpSession, ModelMap model, SearchRequestDetails srchRequestDetails)  {
			SearchResults searchResults = dbSearchController.search(srchRequestDetails, httpSession);
			model.addAttribute("searchRequestDetails", srchRequestDetails);
			model.addAttribute("searchResults", searchResults);
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
			return "SearchResultsPage";	
	}
	
	@GetMapping("/")
	public String getItemDetailsById() {
		return "ItemDetailsPage";
	}

//	@RequestMapping("/")
//	String entry() {
//		return "Home.jsp";
//	} 	

	@GetMapping("/signIn")
	public String getSignInForm(User user) {
		return "SignInForm";
	}

	@PostMapping("/signIn")
	public String processSignInForm(HttpSession httpSession, ModelMap model, User user) {
		try {
			ILibraryFactory factory = new LibraryControllerFactory();
			IUserBasicInfo userBasicInfo = new UserBasicInfo();
			userBasicInfo.setEmail(user.getEmail());
			userBasicInfo.setPwd(user.getPassword());
			LibraryFactorySingleton.instance().build(factory);
			list = LibraryFactorySingleton.instance().getFactory()
					.signIn(userBasicInfo, httpSession).authenticateSignIn();

			for (int i = 0; i < list.size(); i++) {
				model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
			}
			// model object has by default two values and anytime it gets more than that
			// signifies a validation violation
			if (model.size() > 2) {
				return "SignInForm";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Results";
	}

	@GetMapping("/logOut")
	public String processLogOut(HttpSession httpSession, ModelMap model, User user) {
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			// make DBSearchController listener of AuthenticatedUsers and
			// returnItem/AddItem/deleteItem/updateItem
			AuthenticatedUsers.instance().removeAuthenticatedUser(httpSession);
		}
		return "HomePage";
	}
}
