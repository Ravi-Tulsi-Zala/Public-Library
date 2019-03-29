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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.additem.IAddBookController;
import com.library.additem.IAddMovieController;
import com.library.additem.IAddMusicController;
import com.library.businessModels.Book;
import com.library.businessModels.CoverImage;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.businessModels.User;
import com.library.search.BookSearch;
import com.library.search.IDBSearchController;
import com.library.search.MoviesSearch;
import com.library.search.MusicSearch;
import com.library.search.SearchResults;
import com.library.search.SearchTermsAndPage;
import com.library.search.SearchRequest;
import com.library.signIn.AuthenticatedUsers;
import com.library.signIn.ISignInController;
import com.library.signIn.SignInController;
import com.library.signUp.ISignUpController;
import com.library.validatations.ValidateUserForms;
import com.library.welcomePage.IWelcomeController;
import com.library.welcomePage.WelcomePageController;

@Controller
public class LibraryRoutes implements WebMvcConfigurer {
	private List<Map.Entry<String, String>> list = null;
	@Inject
	private IDBSearchController dbSearchController;
	private static String securityQuestionValue;
	private ILibraryFactory factory = null;
	private LibraryFactorySingleton libraryInstance = null;

	public LibraryRoutes() {
		libraryInstance = LibraryFactorySingleton.instance();
		factory = libraryInstance.getFactory();
	}

	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, User user) {
		try {
			ISignUpController signUpCreate = factory.signUp(user);
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
			model.addAttribute("searchTermsAndPage", new SearchTermsAndPage());
			model.addAttribute("bookSearch", new BookSearch());
			model.addAttribute("musicSearch", new MusicSearch());
			model.addAttribute("moviesSearch", new MoviesSearch());
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
			return "AdvancedSearchPage";
		}
		return "NoAccessToNonAuthenticated";
	}

	@PostMapping("/advancedSearch")
	public String executeAdvancedSearch(HttpSession httpSession, ModelMap model, SearchTermsAndPage termsAndPage,
			BookSearch bookSearch, MusicSearch musicSearch, MoviesSearch moviesSearch) {
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			SearchResults searchResults = executeSearch(httpSession, termsAndPage, bookSearch, musicSearch,
					moviesSearch);
			model.addAttribute("searchResults", searchResults);
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
			return "AdvancedSearchResultsPage";
		}
		return "NoAccessToNonAuthenticated";
	}

	@GetMapping("/basicSearch")
	public String getSimpleSearchPage(ModelMap model) {
		model.addAttribute("searchTermsAndPage", new SearchTermsAndPage());
		return "BasicSearchPage";

	}

	@PostMapping("/basicSearch")
	public String executeSimpleSearch(HttpSession httpSession, ModelMap model, SearchTermsAndPage termsAndPage,
			BookSearch bookSearch, MusicSearch musicSearch, MoviesSearch moviesSearch) {
		SearchResults searchResults = executeSearch(httpSession, termsAndPage, bookSearch, musicSearch, moviesSearch);
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("searchResults", searchResults);
		return "BasicSearchResultsPage";

	}

	private SearchResults executeSearch(HttpSession httpSession, SearchTermsAndPage termsAndPage, BookSearch bookSearch,
			MusicSearch musicSearch, MoviesSearch moviesSearch) {
		SearchRequest sr = new SearchRequest();
		sr.addSearchTermsAndResultsPage(termsAndPage);
		sr.addCategoryToSearchIn(bookSearch);
		sr.addCategoryToSearchIn(musicSearch);
		sr.addCategoryToSearchIn(moviesSearch);
		return dbSearchController.search(sr, httpSession);
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
		Logger logger = LogManager.getLogger(SignInController.class);
		try {
			ISignInController signIn = factory.signIn(user, httpSession);
			list = signIn.validateSignIn();
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
			logger.log(Level.ALL, "Something went wrong while signing in the User, please check detailed logs.", e);
			return "ErrorPage"; // Something went wrong page.
		}
	}

	@GetMapping("/addBook")
	public String mappingsForAddItem(ModelMap model) {

		model.addAttribute("book", new Book());
		model.addAttribute("movie", new Movie());
		model.addAttribute("music", new Music());
		model.addAttribute("coverBook", new CoverImage());
		model.addAttribute("coverMovie", new CoverImage());
		model.addAttribute("coverMusic", new CoverImage());

		return "AddItemPage";
	}

	@RequestMapping("/addBook")
	public String addBookToDatabase(ModelMap model, Book book, CoverImage coverBook) {

		String error, errorBookRoutePage;
		IAddBookController iAddBookController = LibraryFactorySingleton.instance().getFactory().makeAddBookController();
		boolean isBookCreated = iAddBookController.addBookRecordInDatabase(book, coverBook.getCoverImage());

		if (isBookCreated) {
			return "ResponseBook";
		} else {
			error = "Error : Book can not be created! Please try again!";
			model.addAttribute("error", error);
			errorBookRoutePage = mappingsForAddItem(model);
			return errorBookRoutePage;
		}

	}

	@PostMapping("/addMovie")
	public String addMovieToDatabase(ModelMap model, Movie movie, CoverImage coverMovie) {

		String error, errorMovieRoutePage;
		IAddMovieController iAddMovieController = LibraryFactorySingleton.instance().getFactory()
				.makeAddMovieController();
		boolean isMovieCreated = iAddMovieController.addMovieRecordInDatabase(movie, coverMovie.getCoverImage());

		if (isMovieCreated) {
			return "ResponseMovie";
		} else {
			error = "Error : Movie can not be created! Please try again!";
			model.addAttribute("error", error);
			errorMovieRoutePage = mappingsForAddItem(model);
			return errorMovieRoutePage;
		}
	}

	@PostMapping("/addMusic")
	public String addMusicToDatabase(ModelMap model, Music music, CoverImage coverMusic) {

		String error, errorMusicRoutePage;
		IAddMusicController iAddMusicController = LibraryFactorySingleton.instance().getFactory()
				.makeAddMusicController();
		boolean isMusicCreated = iAddMusicController.addMusicRecordInDatabase(music, coverMusic.getCoverImage());

		if (isMusicCreated) {
			return "ResponseMusic";
		} else {
			error = "Error : Music can not be created! Please try again!";
			model.addAttribute("error", error);
			errorMusicRoutePage = mappingsForAddItem(model);
			return errorMusicRoutePage;
		}
	}

	@GetMapping("/welcome")
	public String welcomeBody(ModelMap model, LibraryItem libraryItem) {
		Logger logger = LogManager.getLogger(WelcomePageController.class);
		IWelcomeController welcomeCtrl = factory.welcomePage();
		List<Book> book, favBooks;
		List<Movie> movie, favMovies;
		List<Music> music, favMusic;
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
		IForgotPasswordController fPwdCntrl = factory.forgotPassword(recoverPassword);
		securityQuestionValue = fPwdCntrl.setQuestion();
		model.addAttribute("securityQuestion", securityQuestionValue);
		return "ForgotPassword";
	}

	@PostMapping(value = "/forgotPassword")
	public String processForgotPasswordUserForm(ModelMap model, RecoverPassword recoverPassword) {
		recoverPassword.setSecurityQuestion(securityQuestionValue);
		IForgotPasswordController fPwdCntrl = factory.forgotPassword(recoverPassword);
		if (fPwdCntrl.recoverPassword()) {
			return "Welcome";
		} else {
			return "Welcome";
		}
	}

}
