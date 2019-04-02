package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public class LoanManagementContext {

	private IReturnItemStrategy iReturnItemStrategy;

	public LoanManagementContext(IReturnItemStrategy iReturnItemStrategy) {

		this.iReturnItemStrategy = iReturnItemStrategy;

	}

	public boolean executeReturnItemStrategy(UserItem item) {

		return iReturnItemStrategy.returnItem(item);
	}

}
