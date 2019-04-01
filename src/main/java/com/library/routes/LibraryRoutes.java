package com.library.routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.ForgotPassword.ForgotPasswordController;
import com.library.ForgotPassword.IForgotPasswordController;
import com.library.ForgotPassword.RecoverPassword;
import com.library.additem.IAddBookController;
import com.library.additem.IAddMovieController;
import com.library.additem.IAddMusicController;
import com.library.businessModels.Book;
import com.library.businessModels.Cover;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.businessModels.User;
import com.library.messages.Messages;
import com.library.search.BookSearch;
import com.library.search.IDBSearchController;
import com.library.search.MoviesSearch;
import com.library.search.MusicSearch;
import com.library.search.SearchRequest;
import com.library.search.SearchResults;
import com.library.search.SearchTermsAndPage;
import com.library.signIn.AuthenticatedUsers;
import com.library.signIn.ISignInController;
import com.library.signIn.SignInController;
import com.library.signUp.ISignUpController;
import com.library.signUp.SignUpController;
import com.library.welcomePage.AdminPage;
import com.library.welcomePage.IWelcomeController;
import com.library.welcomePage.WelcomePageController;

@Controller
public class LibraryRoutes implements WebMvcConfigurer {
	private List<Map.Entry<String, String>> list = null;
	@Inject
	private IDBSearchController dbSearchController;
	private static String securityQuestionValue;

	private Messages message;
	private String displayMessage, redirectPage;
	private ILibraryFactory factory = null;
	private LibraryFactorySingleton libraryInstance = null;

	private String redirectToWelcome = Messages.WelcomePageRedirect.getMessage();
	private String redirectToSignIn = Messages.SignInPageRedirect.getMessage();
	private String redirectToSignUp = Messages.SignUpPageRedirect.getMessage();
	private String redirectToForgotPwd = Messages.ForgotPassPageRedirect.getMessage();
	private String redirectToErrorPage = Messages.ErrorPageRedirect.getMessage();
	
	private String gotoSignInPage = "SignInForm";
	private String gotoSignUpPage = "SignUpForm";
	private String gotoWelcomePage = "Welcome";
	private String gotoForgotPwdPage = "ForgotPassword";

	public LibraryRoutes() {
		libraryInstance = LibraryFactorySingleton.instance();
		factory = libraryInstance.getFactory();
	}

	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, User user) {
		Logger logger = LogManager.getLogger(SignUpController.class);
		try {
			ISignUpController signUpCreate = factory.signUp(user);
			list = signUpCreate.validateSignUp();
			for (int i = 0; i < list.size(); i++) {
				model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
			}
			// model object has by default two values; anytime it gets more than that
			// signifies a validation violation
			if (model.size() > 2) {
				return gotoSignUpPage;
			} else {
				return redirectToWelcome;
			}
		} catch (Exception e) {
			logger.log(Level.ALL, "Something went wrong while registering the User, please check detailed logs.", e);
			return redirectToErrorPage;
		}
	}

	@GetMapping("/signUp")
	public String getSignUpForm(User user) {
		return gotoSignUpPage;
	}

	@GetMapping("/advancedSearch")
	public String getAdvancedSearchPage(HttpSession httpSession, ModelMap model) {
		AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, "removeMeFromTheController@mail.com");
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			dbSearchController.clearPreviousSearch(httpSession);
			model.addAttribute("searchTermsAndPage", new SearchTermsAndPage());
			model.addAttribute("bookSearch", new BookSearch());
			model.addAttribute("musicSearch", new MusicSearch());
			model.addAttribute("moviesSearch", new MoviesSearch());
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
			return "AdvancedSearchPage";
		}
		return redirectToWelcome;
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
		return redirectToWelcome;
	}

	@GetMapping("/basicSearch")
	public String getSimpleSearchPage(ModelMap model, HttpSession httpSession) {
		dbSearchController.clearPreviousSearch(httpSession);
		model.addAttribute("searchTermsAndPage", new SearchTermsAndPage());
		addUserEmail(model, httpSession);
		return "BasicSearchPage";
	}

	@PostMapping("/basicSearch")
	public String executeSimpleSearch(HttpSession httpSession, ModelMap model, SearchTermsAndPage termsAndPage,
			BookSearch bookSearch, MusicSearch musicSearch, MoviesSearch moviesSearch) {
		SearchResults searchResults = executeSearch(httpSession, termsAndPage, bookSearch, musicSearch, moviesSearch);
		model.addAttribute("searchResults", searchResults);
		addUserEmail(model, httpSession);
		return "BasicSearchResultsPage";
	}

	private SearchResults executeSearch(HttpSession httpSession, SearchTermsAndPage termsAndPage, BookSearch bookSearch,
			MusicSearch musicSearch, MoviesSearch moviesSearch) {
		SearchRequest sr = new SearchRequest();
		sr.setTermsAndPage(termsAndPage);
		sr.addCategoryToSearchIn(bookSearch);
		sr.addCategoryToSearchIn(musicSearch);
		sr.addCategoryToSearchIn(moviesSearch);
		return dbSearchController.search(sr, httpSession);
	}

	private void addUserEmail(ModelMap model, HttpSession httpSession) {
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
		}
	}

	@GetMapping("/")
	public String getItemDetailsById() {
		return "ItemDetailsPage";
	}

	@GetMapping("/signIn")
	public String getSignInForm(User user) {
		return gotoSignInPage;
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
				return gotoSignInPage;
			}
			return signIn.checkUserCredential();
		} catch (Exception e) {
			logger.log(Level.ALL, "Something went wrong while signing in the User, please check detailed logs.", e);
			return redirectToErrorPage; // Something went wrong page.
		}
	}

	@GetMapping("/addBook")
	public String mappingsForAddItem(ModelMap model) {

		model.addAttribute("book", new Book());
		model.addAttribute("movie", new Movie());
		model.addAttribute("music", new Music());
		model.addAttribute("coverBook", new Cover());
		model.addAttribute("coverMovie", new Cover());
		model.addAttribute("coverMusic", new Cover());

		return "AddItemPage";
	}

	@RequestMapping("/addBook")
	public String addBookToDatabase(ModelMap model, Book book, Cover coverBook) {

		IAddBookController iAddBookController = LibraryFactorySingleton.instance().getFactory().makeAddBookController();
		message = iAddBookController.addBookRecordInDatabase(book, coverBook.getCoverImage());
		displayMessage = message.getMessage();
		model.addAttribute("message", displayMessage);
		redirectPage = mappingsForAddItem(model);
		return redirectPage;

	}

	@PostMapping("/addMovie")
	public String addMovieToDatabase(ModelMap model, Movie movie, Cover coverMovie) {

		IAddMovieController iAddMovieController = LibraryFactorySingleton.instance().getFactory()
				.makeAddMovieController();
		message = iAddMovieController.addMovieRecordInDatabase(movie, coverMovie.getCoverImage());
		displayMessage = message.getMessage();
		model.addAttribute("message", displayMessage);
		redirectPage = mappingsForAddItem(model);
		return redirectPage;

	}

	@PostMapping("/addMusic")
	public String addMusicToDatabase(ModelMap model, Music music, Cover coverMusic) {

		IAddMusicController iAddMusicController = LibraryFactorySingleton.instance().getFactory()
				.makeAddMusicController();
		message = iAddMusicController.addMusicRecordInDatabase(music, coverMusic.getCoverImage());
		displayMessage = message.getMessage();
		model.addAttribute("message", displayMessage);
		redirectPage = mappingsForAddItem(model);
		return redirectPage;
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
			return redirectToErrorPage;
		} catch (Exception e) {
			logger.log(Level.ALL, "Some problem occured, check logs.", e);
			return redirectToErrorPage;
		}
		model.addAttribute("book", book);
		model.addAttribute("favBooks", favBooks);
		model.addAttribute("movie", movie);
		model.addAttribute("favMovies", favMovies);
		model.addAttribute("music", music);
		model.addAttribute("favMusic", favMusic);
		model.addAttribute("isAdminAval", welcomeCtrl.isAdminAvailable());
		model.addAttribute("loggingStatus", AdminPage.getLoggingStatus());
		model.addAttribute("sessionClient", AdminPage.getAvailableUserID());
		return gotoWelcomePage;
	}

	@GetMapping("/ErrorPage")
	public String errorPage() {
		return "ErrorPage";
	}

	@GetMapping("/logOut")
	public String processLogOut(HttpSession httpSession, ModelMap model, User user) {
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			AuthenticatedUsers.instance().removeAuthenticatedUser(httpSession);
		}
		return redirectToWelcome;
	}

	@GetMapping(value = "/forgotPassword")
	public String getForgotPasswordForm(ModelMap model, RecoverPassword recoverPassword) {
		Logger logger = LogManager.getLogger(ForgotPasswordController.class);
		try {
			IForgotPasswordController fPwdCntrl = factory.forgotPassword(recoverPassword);
			securityQuestionValue = fPwdCntrl.setQuestion();
			model.addAttribute("securityQuestion", securityQuestionValue);
		} catch (Exception e) {
			logger.log(Level.ALL, "Some generic error occured while in forgotPassword controller.", e);
		}
		return gotoForgotPwdPage;
	}

	@PostMapping(value = "/forgotPassword")
	public String processForgotPasswordUserForm(RecoverPassword recoverPassword) {
		Logger logger = LogManager.getLogger(ForgotPasswordController.class);
		try {
			recoverPassword.setSecurityQuestion(securityQuestionValue);
			IForgotPasswordController fPwdCntrl = factory.forgotPassword(recoverPassword);
			if (fPwdCntrl.recoverPassword()) {
				return redirectToSignIn;
			} else {
				return redirectToForgotPwd;
			}
		} catch (MessagingException | IOException em) {
			logger.log(Level.ALL, "Some problem occured while sending a email.", em);
			return redirectToErrorPage;
		} catch (Exception e) {
			logger.log(Level.ALL, "Some generic error occured while in forgotPassword controller.", e);
			return redirectToErrorPage;
		}
	}

}
