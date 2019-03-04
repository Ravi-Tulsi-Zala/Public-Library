package com.library.IDAO;

import com.mysql.jdbc.Blob;

public interface ICoverDAO {
	public Blob getCover(int itemID);
	public void setCover(int itemID, Blob cover);
	public Boolean isPresent(int itemID);
	public void deleteCover(int itemID);
	public void editCover(int itemID, Blob cover);
}
