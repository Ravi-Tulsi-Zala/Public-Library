package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public class MusicReturnStrategy implements IReturnItemStrategy {

	@Override
	public boolean returnItem(UserItem ittem) {
		
		return false;
	}

}
