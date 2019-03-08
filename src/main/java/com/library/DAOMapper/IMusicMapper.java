package com.library.DAOMapper;

import java.sql.ResultSet;

import com.library.businessModels.Music;

public interface IMusicMapper {
	public Music mapMusic(ResultSet resultSet);
}
