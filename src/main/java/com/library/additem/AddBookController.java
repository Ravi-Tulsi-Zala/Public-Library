package com.library.additem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;

public class AddBookController {
	IDAOFactory factory;

	public AddBookController() {
		
	}

	public void addBookRecordInDatabase(Book book) {
		factory = new DAOFactory();
		IBookDAO bookDAO = factory.makeBookDAO();
		bookDAO.createBook(book);
	}

}
