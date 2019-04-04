package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public class MovieReturnStrategy implements IReturnItemStrategy {

	@Override
	public void returnItem(UserItem item) {
		
	
	}

	@Override
	public void sendEmail(UserItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemOnHold(int itemId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public UserItem getTheNextUserInLine(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUserFromHold(UserItem userOnHold) {
		// TODO Auto-generated method stub
		
	}

}
