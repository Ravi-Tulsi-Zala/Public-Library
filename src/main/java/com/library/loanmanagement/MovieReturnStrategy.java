package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public class MovieReturnStrategy implements IReturnItemStrategy {

	@Override
	public boolean returnItem(UserItem item) {
		
		return false;
	}

}
