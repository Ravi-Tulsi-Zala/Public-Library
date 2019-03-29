package com.library.additem;

import org.springframework.web.multipart.MultipartFile;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.messages.Messages;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryControllerFactory;
import com.library.routes.LibraryFactorySingleton;

public class AddBookController implements IAddBookController {
	IDAOFactory iDAOfactory;
	int itemIdOfBook;
	IBookDAO bookDAO;
	boolean isBookCreated, isBookCoverCreated,isDuplicateBook;

	public AddBookController() {
		iDAOfactory = new DAOFactory();
		bookDAO = iDAOfactory.makeBookDAO();
		ILibraryFactory iLibraryfactory = new LibraryControllerFactory();
		LibraryFactorySingleton.instance().build(iLibraryfactory);
	}

	public Messages addBookRecordInDatabase(Book book, MultipartFile bookCoverImage) {

		isDuplicateBook = bookDAO.checkBookDuplicacy(book);
		
		if (isDuplicateBook) {
			return Messages.ERROR_DUPLICATE_BOOK;
		}
		
		itemIdOfBook = bookDAO.createBook(book);

		if (itemIdOfBook == 0) {

			return Messages.ERROR_BOOK_CAN_NOT_BE_CREATED;

		} else {
			isBookCoverCreated = LibraryFactorySingleton.instance().getFactory().makeItemCoverSetter()
					.isCoverAddedToDatabase(itemIdOfBook, bookCoverImage);
			return Messages.SUCCESS_BOOK;

		}

	}

}
