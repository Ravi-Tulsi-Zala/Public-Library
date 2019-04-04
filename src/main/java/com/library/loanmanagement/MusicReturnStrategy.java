package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public class MusicReturnStrategy implements IReturnItemStrategy {

	@Override
	public void returnItem(UserItem ittem) {
		

	}

	@Override
	public void sendEmail(UserItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemOnHold(UserItem item) {
		// TODO Auto-generated method stub
		return false;
	}

}
