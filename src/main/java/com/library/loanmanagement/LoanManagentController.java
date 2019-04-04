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
	LoanManagementContext context;
	IReturnItemStrategy iReturnItemStrategy;

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

	@Override
	public void removeUserItems(List<UserItem> userItems) {

		for (UserItem item : userItems) {
			itemDAO.removeItem(item);
			increaseAvailability(item);
		}

	}

	private void increaseAvailability(UserItem item) {

		String category = item.getCategory();

		if (category.equalsIgnoreCase(CategoryEnum.BOOK.getText())) {
			iReturnItemStrategy = new BookReturnStrategy();
			context = new LoanManagementContext(iReturnItemStrategy);
			context.executeReturnItemStrategy(item);
		} else if (category.equalsIgnoreCase(CategoryEnum.MOVIE.getText())) {
			iReturnItemStrategy = new MovieReturnStrategy();
			context = new LoanManagementContext(iReturnItemStrategy);
			context.executeReturnItemStrategy(item);
		} else if (category.equalsIgnoreCase(CategoryEnum.MUSIC.getText())) {
			iReturnItemStrategy = new MusicReturnStrategy();
			context = new LoanManagementContext(iReturnItemStrategy);
			context.executeReturnItemStrategy(item);
		}

	}

}
