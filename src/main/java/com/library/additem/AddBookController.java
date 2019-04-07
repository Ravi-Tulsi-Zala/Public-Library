package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Book;
import com.library.dao.IBookDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryFactorySingleton;

public class AddBookController implements IAddBookController {
	
	private IDAOFactory iDAOfactory;
	private int itemIdOfBook;
	private IBookDAO bookDAO;
	private ILibraryFactory iLibraryfactory;
	private LibraryFactorySingleton factorySingleton;
	private IItemCoverSetter coverSetter;
	private boolean isBookCoverCreated, isDuplicateBook;

	public AddBookController() {
		iDAOfactory = new DAOFactory();
		bookDAO = iDAOfactory.makeBookDAO();
		factorySingleton = LibraryFactorySingleton.instance();
		iLibraryfactory = factorySingleton.getFactory();

	}

	public AddItemMessagesEnum addBookRecordInDatabase(Book book, MultipartFile bookCoverImage) {

		isDuplicateBook = bookDAO.checkBookDuplicacy(book);

		if (isDuplicateBook) {
			return AddItemMessagesEnum.ERROR_DUPLICATE_BOOK;
		}

		itemIdOfBook = bookDAO.createBook(book);

		if (itemIdOfBook == 0) {

			return AddItemMessagesEnum.ERROR_BOOK_CAN_NOT_BE_CREATED;

		} else {

			coverSetter = iLibraryfactory.makeItemCoverSetter();
			isBookCoverCreated = coverSetter.isCoverAddedToDatabase(itemIdOfBook, bookCoverImage);
			if (isBookCoverCreated) {
				return AddItemMessagesEnum.SUCCESS_BOOK;
			} else {
				return AddItemMessagesEnum.ERROR_BOOK_CAN_NOT_BE_CREATED;
			}
		}

	}

}
