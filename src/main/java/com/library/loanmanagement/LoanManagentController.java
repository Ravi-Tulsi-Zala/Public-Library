package com.library.loanmanagement;

import java.util.ArrayList;
import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IUserItemDAO;
import com.library.businessModels.UserItem;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryFactorySingleton;

public class LoanManagentController implements ILoanManagementController {

	IDAOFactory iDAOfactory;
	ILibraryFactory iLibraryfactory;
	LibraryFactorySingleton factorySingleton;
	IUserItemDAO itemDAO;
	List<UserItem> items;

	public LoanManagentController() {

		iDAOfactory = new DAOFactory();
		itemDAO = iDAOfactory.makeUserItemDAO();
		factorySingleton = LibraryFactorySingleton.instance();
		iLibraryfactory = factorySingleton.getFactory();
		items = new ArrayList<UserItem>();
	}

	@Override
	public List<UserItem> getAllBorrowedItems() {

		items = itemDAO.getAllBorrowedItems();

		return items;
	}

}
