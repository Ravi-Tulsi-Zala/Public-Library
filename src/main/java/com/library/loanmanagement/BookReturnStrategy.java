package com.library.loanmanagement;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.UserItem;

public class BookReturnStrategy implements IReturnItemStrategy {

	IDAOFactory iDAOfactory;
	IBookDAO iBookDAO;

	public BookReturnStrategy() {
		iDAOfactory = new DAOFactory();
		iBookDAO = iDAOfactory.makeBookDAO();
	}

	@Override
	public void returnItem(UserItem item) {

		int itemId = item.getItemId();
		int currentAvailability = iBookDAO.getAvailability(itemId);
		int udatedAvailability = currentAvailability + 1;
		iBookDAO.updateAvailability(itemId, udatedAvailability);

	}

	@Override
	public void sendEmail(UserItem item) {
		
		int itemId = item.getItemId();
		
		
	}

	@Override
	public boolean isItemOnHold(UserItem item) {
		
		return false;
	}
	
	
}
