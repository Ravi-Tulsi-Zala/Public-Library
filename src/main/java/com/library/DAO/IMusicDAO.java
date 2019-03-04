package com.library.DAO;

import java.util.ArrayList;
import java.util.List;

import com.library.BusinessModels.Music;;

public interface IMusicDAO {

	public Music getMusicById(int itemID);
	public Music getMusicByTitle(String musicTitle);
	public List<Music> getMusicByArtistName(String musicArtistName);
	public List<Music> getMusicByCategory(String category);
	public List<Music> getMusicByRecordLabel(String recordLabel);
	public Boolean createMusic(Music music);
	public Boolean updateMusic(Music music);
	public Boolean deleteMusic(Music music); 
	
	
	
}
