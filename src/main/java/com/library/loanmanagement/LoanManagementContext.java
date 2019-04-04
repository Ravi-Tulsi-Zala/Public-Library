package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public class LoanManagementContext {

	private IReturnItemStrategy iReturnItemStrategy;

	public LoanManagementContext(IReturnItemStrategy iReturnItemStrategy) {

		this.iReturnItemStrategy = iReturnItemStrategy;

	}

	public void executeReturnItemStrategy(UserItem item) {
		
		boolean isItemOnHold=false;
		iReturnItemStrategy.returnItem(item);
		iReturnItemStrategy.isItemOnHold(item);
		iReturnItemStrategy.sendEmail(item);
	}

}
