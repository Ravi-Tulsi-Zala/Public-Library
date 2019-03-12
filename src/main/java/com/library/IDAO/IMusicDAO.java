package com.library.IDAO;

import java.util.List;

import com.library.businessModels.Music;
import com.library.itemSearch.IMusicSearchRequestDetails;

public interface IMusicDAO {
	public int[] search(IMusicSearchRequestDetails requestDetails);
	public Music getMusicById(int itemID);
	public List<Music> getMusicByTitle(String musicTitle);
	public List<Music> getMusicByArtistName(String musicArtistName);
	public List<Music> getMusicByCategory(String category);
	public List<Music> getMusicByRecordLabel(String recordLabel);
	public Boolean createMusic(Music music);
	public Boolean updateMusic(Music music);
	public Boolean deleteMusic(Music music); 
}
