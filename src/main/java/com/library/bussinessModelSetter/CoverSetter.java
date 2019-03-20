package com.library.bussinessModelSetter;

import java.sql.ResultSet;

import com.library.IBussinessModelSetter.ICoverMapper;
import com.library.businessModels.Cover;
import com.mysql.jdbc.Blob;

public class CoverSetter implements ICoverMapper {
	
	@Override
	public Cover mapCover(ResultSet resultSet) {
		try {
			Cover cover = new Cover();
			cover.setItemID(resultSet.getInt("Item_ID"));
			cover.setCoverBlob((Blob) resultSet.getBlob("Cover_Blob"));
			cover.setFileExtension(resultSet.getString("File_Extension"));
			return cover;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
