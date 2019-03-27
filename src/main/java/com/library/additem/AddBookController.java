package com.library.additem;

import org.springframework.web.multipart.MultipartFile;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.controllers.ILibraryFactory;
import com.library.controllers.LibraryControllerFactory;
import com.library.controllers.LibraryFactorySingleton;

public class AddBookController implements IAddBookController {
	IDAOFactory iDAOfactory;
	int itemIdOfBook;
	IBookDAO bookDAO;
	boolean isBookCreated, isBookCoverCreated;

	public AddBookController() {
		iDAOfactory = new DAOFactory();
		bookDAO = iDAOfactory.makeBookDAO();
		ILibraryFactory iLibraryfactory = new LibraryControllerFactory();
		LibraryFactorySingleton.instance().build(iLibraryfactory);
	}

	public boolean addBookRecordInDatabase(Book book, MultipartFile bookCoverImage) {

		itemIdOfBook = bookDAO.createBook(book);

		if (itemIdOfBook == 0) {
			isBookCreated = false;
		} else {
			isBookCoverCreated = LibraryFactorySingleton.instance().getFactory().makeItemCoverSetter()
					.isCoverAddedToDatabase(itemIdOfBook, bookCoverImage);
			isBookCreated = true;
		}
		return (isBookCreated && isBookCoverCreated);

	}

}
