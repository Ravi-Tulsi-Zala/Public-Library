package com.library.additem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;

public class AddBookController {
	IDAOFactory factory;

	public AddBookController() {
		factory = new DAOFactory();
	}

	public boolean addBookRecordInDatabase(Book book) {
		
		IBookDAO bookDAO = factory.makeBookDAO();
		boolean isBookCreated = bookDAO.createBook(book);
		
		return  isBookCreated;
		
	}

}
