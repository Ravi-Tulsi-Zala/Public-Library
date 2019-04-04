package com.library.browsePage;

import java.util.List;

import com.library.BussinessModelSetter.DisplaySetter;
import com.library.BussinessModelSetter.IDisplaySetter;
import com.library.DAO.IMusicDAO;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.businessModels.Display;
import com.library.businessModels.Music;

public class BrowseMusic implements IBrowseDisplayObjects{

	private IMusicDAO musicDAO;
	private String itemType;
	
	public BrowseMusic()
	{
		IDAOFactory factory = new DAOFactory();
		musicDAO = factory.makeMusicDAO();	
		itemType = "Music";
	}
	
	@Override
	public List<Display> itemsByCategory(String category) {
		IDisplaySetter displaySetter = new DisplaySetter();
		List<Music> music = musicDAO.getMusicByCategory(category);
		List<Display> displayMusic = displaySetter.getMusicDisplayObjects(music);
		return displayMusic;
	}

	@Override
	public List<String> getCategories() {
		List<String> categories = musicDAO.getMusicCategories();
		return categories;
	}

	@Override
	public String getItemType() {
		return itemType;
	}

}
