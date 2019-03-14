package com.library.businessModels;

public abstract class LibraryItem {
	private String category;
	private String title;
	private int itemID;
	private int availablity;
	
	public int getAvailability() {
		return availablity;
	}
	public void setAvailability(int availablity) {
		this.availablity = availablity;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
