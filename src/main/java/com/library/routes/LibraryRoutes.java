package com.library.routes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.additem.AddBookController;
import com.library.additem.AddMovieController;
import com.library.additem.AddMusicController;
import com.library.businessModels.Book;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.businessModels.User;
import com.library.search.AdvancedSearchRequest;
import com.library.search.BasicSearchRequest;
import com.library.search.IDBSearchController;
import com.library.search.SearchResults;
import com.library.signIn.AuthenticatedUsers;
import com.library.signIn.ISignInController;
import com.library.signUp.ISignUpController;
import com.library.welcomePage.IWelcomeController;
import com.library.welcomePage.WelcomePageController;

@Controller
public class LibraryRoutes implements WebMvcConfigurer {
	private List<Map.Entry<String, String>> list = null;
	@Inject
	private IDBSearchController dbSearchController;
	private static String securityQuestionValue;

	public LibraryRoutes() {
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
			AdvancedSearchRequest searchRequestDetails = new AdvancedSearchRequest();
			model.addAttribute("advancedSearchRequest", searchRequestDetails);
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
			return "AdvancedSearchPage";
		}
		return "NoAccessToNonAuthenticated";
	}

	@PostMapping("/advancedSearch")
	public String executeAdvancedSearch(HttpSession httpSession, ModelMap model,
			AdvancedSearchRequest srchRequestDetails) {
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			SearchResults searchResults = dbSearchController.search(srchRequestDetails, httpSession);
			model.addAttribute("searchResults", searchResults);
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
			return "AdvancedSearchResultsPage";
		}
		return "NoAccessToNonAuthenticated";
	}

	@GetMapping("/basicSearch")
	public String getSimpleSearchPage(ModelMap model) {
		BasicSearchRequest basic = new BasicSearchRequest();
		model.addAttribute("basicSearchRequest", basic);
		return "BasicSearchPage";

	}

	@PostMapping("/basicSearch")
	public String executeSimpleSearch(HttpSession httpSession, ModelMap model, BasicSearchRequest basic) {
		AdvancedSearchRequest advanced = new AdvancedSearchRequest();
		advanced.setSearchTerms(basic.getSearchTerms());
		advanced.setRequestedResultsPageNumber(basic.getRequestedResultsPageNumber());
		SearchResults searchResults = dbSearchController.search(advanced, httpSession);
		model.addAttribute("searchResults", searchResults);
		return "BasicSearchResultsPage";

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
		Logger logger = LogManager.getLogger(WelcomePageController.class);
		IWelcomeController welcomeCtrl = LibraryFactorySingleton.instance().getFactory().welcomePage();
		List<Book> book,favBooks;
		List<Movie> movie,favMovies;
		List<Music> music,favMusic;
		try {
			book = welcomeCtrl.getBookItems();
			movie = welcomeCtrl.getMovieItems();
			music = welcomeCtrl.getMusicItems();
			favBooks = welcomeCtrl.getFavouriteBooks();
			favMovies = welcomeCtrl.getFavouriteMovies();
			favMusic = welcomeCtrl.getFavouriteMusic();
		} catch (SQLException e) {
			logger.log(Level.ALL, "Some problem occured while connection with Database in welcome controller.", e);
			return "redirect:ErrorPage";
		} catch (Exception e) {
			logger.log(Level.ALL, "Some problem occured, check logs.", e);
			return "redirect:ErrorPage";
		}
		model.addAttribute("book", book);
		model.addAttribute("favBooks", favBooks);
		model.addAttribute("movie", movie);
		model.addAttribute("favMovies", favMovies);
		model.addAttribute("music", music);
		model.addAttribute("favMusic", favMusic);
		model.addAttribute("isAdminAval", welcomeCtrl.isAdminAvailable());
		return "Welcome";
	}

	@GetMapping("/ErrorPage")
	public String errorPage() {
		return "ErrorPage";
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
