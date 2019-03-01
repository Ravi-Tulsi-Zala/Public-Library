package com.library.business.itemSearch;

public class ItemDescriptor {
	private String itemDescription;
	private String itemImageUrl;
	private String itemID;
	
	public ItemDescriptor(String itemDescription, String itemImageUrl, String itemID) {
		this.itemDescription = itemDescription;
		this.itemImageUrl = itemImageUrl;
		this.itemID = itemID;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemImageUrl() {
		return itemImageUrl;
	}
	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemURL) {
		this.itemID = itemURL;
	}
}
