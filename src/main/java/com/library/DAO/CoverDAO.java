package com.library.DAO;

import com.library.IDAO.ICoverDAO;
import com.mysql.jdbc.Blob;

public class CoverDAO implements ICoverDAO {

	@Override
	public Blob getCover(int itemID) {
		
		return null;
	}

	@Override
	public void setCover(int itemID, Blob coverBlob) {
	
		
	}

	@Override
	public Boolean isPresent(int itemID) {
		
		return null;
	}

	@Override
	public void deleteCover(int itemID) {
		
	}

	@Override
	public void editCover(int itemID, Blob cover) {
		
	}

}
