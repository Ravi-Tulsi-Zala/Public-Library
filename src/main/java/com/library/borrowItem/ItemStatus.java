package com.library.borrowItem;

public class ItemStatus {
	
	public Boolean isItemAvailable(int itemID)
	{
		return true;
	}
	
	public Boolean isItemAlreadyBooked(String userEmail, int itemId)
	{
		return true;
	}
	
	public Boolean isItemOnHold(String userEmail, int itemId)
	{
		return true;
	}
}
