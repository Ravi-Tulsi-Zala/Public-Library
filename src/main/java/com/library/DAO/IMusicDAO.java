package com.library.DAO;

import java.util.ArrayList;

import com.library.POJO.Music;;

public interface IMusicDAO {

	public Music getMusicById(int itemID);
	public Music getMusicByTitle(String musicTitle);
	public ArrayList<Music> getMusicByArtistName(String musicArtistName);
	public ArrayList<Music> getMusicByCategory(String category);
	public ArrayList<Music> getMusicByDescription(String musicDescription);
	public void createMusic(Music music);
	public void updateMusic(Music music);
	public void deleteMusic(Music music); 
	
	
	
}
