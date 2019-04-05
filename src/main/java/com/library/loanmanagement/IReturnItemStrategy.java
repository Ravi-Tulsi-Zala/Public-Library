package com.library.loanmanagement;

import com.library.businessModels.UserItem;

public interface IReturnItemStrategy {
	
	public void returnItem(UserItem item);

	public void sendEmail(UserItem item);

	public boolean isItemOnHold(int itemId);
	
	public UserItem getTheNextUserInLine(int itemId);

	public void removeUserFromHold(UserItem userOnHold);
}
