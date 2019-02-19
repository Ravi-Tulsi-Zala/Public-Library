package com.library.buisness.population;

import java.util.Date;

public class UserItem implements IUserItem {
	
	private final String itemId;
	private final Date timeStamp;
	private boolean isBorrowed;
	
	public UserItem(String itemId, Date timeStamp, boolean isBorowed) {
		this.itemId = itemId;
		this.timeStamp = timeStamp;
		this.isBorrowed = isBorowed;
	}

	@Override
	public String getItemId() {
		return itemId;
	}

	@Override
	public Date getTimeStamp() {
		return timeStamp;
	}

	@Override
	public boolean isBorrowed() {
		return isBorrowed;
	}

	@Override
	public boolean isOnHold() {
		return !isBorrowed;
	}

	@Override
	public void setIsBorowed() {
		isBorrowed = true;
	}

}
