package com.library.BusinessModels;

import com.mysql.jdbc.Blob;

public class Cover {
	int itemID;
	Blob cover;
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public Blob getCover() {
		return cover;
	}
	public void setCover(Blob cover) {
		this.cover = cover;
	}
		
}
