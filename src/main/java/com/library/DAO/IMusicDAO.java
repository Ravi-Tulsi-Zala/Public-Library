package com.library.DAO;

import java.util.ArrayList;
import java.util.List;

import com.library.BusinessModels.Music;;

public interface IMusicDAO {

	public Music getMusicById(int itemID);
	public Music getMusicByTitle(String musicTitle);
	public List<Music> getMusicByArtistName(String musicArtistName);
	public List<Music> getMusicByCategory(String category);
	public List<Music> getMusicByDescription(String musicDescription);
	public void createMusic(Music music);
	public void updateMusic(Music music);
	public void deleteMusic(Music music); 
	
	
	
}
