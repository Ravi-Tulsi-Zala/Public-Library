package com.library.DAOMapperImpl;

import java.sql.ResultSet;
import com.library.DAOMapper.ICoverMapper;
import com.library.businessModels.Cover;

public class CoverMapper implements ICoverMapper {
	
	@Override
	public Cover mapCover(ResultSet resultSet) {
		try {
			Cover cover = new Cover();
			cover.setItemID(resultSet.getInt("Item_ID"));
			cover.setCoverBlob(resultSet.getBlob("Cover_Blob"));
			cover.setFileExtension(resultSet.getString("File_Extension"));
			return cover;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
