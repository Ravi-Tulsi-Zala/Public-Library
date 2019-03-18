package com.library.DAOMapper;
	import java.sql.ResultSet;
	import com.library.businessModels.Cover;

public interface ICoverMapper {
	public Cover mapCover(ResultSet resultSet);
}
