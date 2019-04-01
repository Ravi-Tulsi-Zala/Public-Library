package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public interface IReturnItemStrategy {
	
	public boolean returnItem(UserItem item);

}
