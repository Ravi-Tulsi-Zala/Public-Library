package com.library.controllers;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.library.ForgotPassword.RecoverPassword;
import com.library.ForgotPassword.ForgotPasswordController;
import com.library.ForgotPassword.IForgotPasswordController;
import com.library.additem.AddBookController;
import com.library.additem.AddMovieController;
import com.library.additem.AddMusicController;
import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.businessModels.User;
import com.library.mockDB.WelcomePageMocked;
import com.library.search.DBSeachControllerBean;
import com.library.search.IDBSearchController;
import com.library.search.SearchRequestDetails;
import com.library.search.SearchResults;
import com.library.signIn.AuthenticatedUsers;
import com.library.signIn.ISignInController;
import com.library.signUp.ISignUpController;
import com.library.welcomePage.Manager;
import com.library.welcomePage.WelcomePageController;

@ComponentScan(basePackages = { "com.library.model" }, basePackageClasses = DBSeachControllerBean.class)
@Controller
public class LibraryController implements WebMvcConfigurer {
	private List<Map.Entry<String, String>> list = null;
	@Inject
	private IDBSearchController dbSearchController;
	private static String securityQuestionValue;

	public LibraryController() {
		ILibraryFactory factory = new LibraryControllerFactory();
		LibraryFactorySingleton.instance().build(factory);

	}

	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, User user) {
		try {
			ISignUpController signUpCreate = LibraryFactorySingleton.instance().getFactory().signUp(user);
			list = signUpCreate.authenticateSignUp();
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
		AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, "removeMeFromTheController@mail.com");
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

	public String getSearchResults(HttpSession httpSession, ModelMap model, SearchRequestDetails srchRequestDetails) {
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

	@GetMapping("/signIn")
	public String getSignInForm(User user) {
		return "SignInForm";
	}

	@PostMapping("/signIn")
	public String processSignInForm(HttpSession httpSession, ModelMap model, User user) {
		try {
			ISignInController signIn = LibraryFactorySingleton.instance().getFactory().signIn(user, httpSession);
			list = signIn.authenticateSignIn();
			for (int index = 0; index < list.size(); index++) {
				model.addAttribute(list.get(index).getKey(), list.get(index).getValue());
			}
			// ModelMap by default has two values and anytime it gets more than that
			// signifies validation violation
			if (model.size() > 2) {
				return "SignInForm";
			}
			return signIn.checkUserCredential();
		} catch (Exception e) {
			e.printStackTrace();
			return "ErrorPage"; // Something went wrong page.
		}
	}

	@GetMapping("/addBook")
	public String mappingsForAddItem(ModelMap model) {

		model.addAttribute("book", new Book());
		model.addAttribute("movie", new Movie());
		model.addAttribute("music", new Music());

		return "AddItemPage";
	}

	@PostMapping("/addBook")
	public String addBookToDatabase(ModelMap model, Book book) {

		AddBookController addBookController = new AddBookController();
		Boolean isBookCreated = addBookController.addBookRecordInDatabase(book);

		if (isBookCreated) {
			return "ResponseBook";
		} else {
			String error = "Error : Book can not be created! Please try again!";
			model.addAttribute("movie", new Movie());
			model.addAttribute("music", new Music());
			model.addAttribute("error", error);
			return "AddItemPage";
		}

	}

	@PostMapping("/addMovie")
	public String addMovieToDatabase(ModelMap model, Movie movie) {

		AddMovieController addMovieController = new AddMovieController();
		addMovieController.addMovieRecordInDatabase(movie);

		return "ResponseMovie";
	}

	@PostMapping("/addMusic")
	public String addMusicToDatabase(ModelMap model, Music music) {

		AddMusicController addMusicController = new AddMusicController();
		addMusicController.addMusicRecordInDatabase(music);

		return "ResponseMusic";
	}

	@GetMapping("/welcome")
	public String welcomeBody(ModelMap model, LibraryItem libraryItem) {
		model.addAttribute("isAdminAval", true);
		List<Book> book = new WelcomePageController().getBookItems();
		List<Movie> movie = new WelcomePageController().getMovieItems();
		List<Music> music = new WelcomePageController().getMusicItems();
		model.addAttribute("book", book);
		model.addAttribute("movie", movie);
		model.addAttribute("music", music);
		return "Welcome";
	}

	@PostMapping("/welcome")
	public String welcomeProcess(ModelMap model, Movie movie) {
		model.addAttribute("movie", new WelcomePageMocked().initiateMock());
		return "Welcome";
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

	@GetMapping(value = "/forgotPassword")
	public String getForgotPasswordForm(ModelMap model, RecoverPassword recoverPassword) {
		IForgotPasswordController fPwdCntrl = LibraryFactorySingleton.instance().getFactory()
				.forgotPassword(recoverPassword);
		securityQuestionValue = fPwdCntrl.setQuestion();
		model.addAttribute("securityQuestion", securityQuestionValue);
		return "ForgotPassword";
	}

	@PostMapping(value = "/forgotPassword")
	public String processForgotPasswordUserForm(ModelMap model, RecoverPassword recoverPassword) {
		recoverPassword.setSecurityQuestion(securityQuestionValue);
		IForgotPasswordController fPwdCntrl = LibraryFactorySingleton.instance().getFactory()
				.forgotPassword(recoverPassword);
		if (fPwdCntrl.recoverPassword()) {
			return "Welcome";
		} else {
			return "Welcome";
		}
	}
}
