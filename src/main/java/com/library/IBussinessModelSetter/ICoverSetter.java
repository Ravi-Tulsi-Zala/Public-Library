package com.library.IBussinessModelSetter;
	import java.sql.ResultSet;
	import com.library.businessModels.Cover;

public interface ICoverSetter {
	public Cover mapCover(ResultSet resultSet);
}
