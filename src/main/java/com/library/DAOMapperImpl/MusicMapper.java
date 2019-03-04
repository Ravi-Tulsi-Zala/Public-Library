package com.library.DAOMapperImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.BusinessModels.Music;
import com.library.DAOMapper.IMusicMapper;

public class MusicMapper implements IMusicMapper{

	@Override
	public Music mapMusic(ResultSet resultSet) {
		
		Music music = new Music();
		try {
			music.setTitle(resultSet.getString("Title"));
			music.setCategory(resultSet.getString("Category"));
			music.setRecordLabel(resultSet.getString("Record_Label"));
			music.setArtist(resultSet.getString("Artist"));
			music.setAvailability(resultSet.getInt("Availability"));
			music.setItemID(resultSet.getInt("Item_ID"));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		return music;
	}
}
