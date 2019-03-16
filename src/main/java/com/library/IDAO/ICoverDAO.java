package com.library.IDAO;

import java.sql.Blob;

public interface ICoverBlobDAO {
	public Blob getCoverBlobByID(int itemID);
	public boolean setCoverBlobByID(int itemID, Blob cover);
	public boolean deleteCoverBlobByID(int itemID);
}
