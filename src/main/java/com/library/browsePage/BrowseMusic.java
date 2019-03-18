package com.library.browsePage;

import java.util.List;

import com.library.BussinessModelSetter.DisplaySetter;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IBussinessModelSetter.IDisplaySetter;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Display;
import com.library.businessModels.Music;

public class BrowseMusic implements IBrowseDisplayObjects{

	IMusicDAO musicDAO;
	IDisplaySetter displaySetter = new DisplaySetter();
	
	public BrowseMusic()
	{
		IDAOFactory factory = new DAOFactory();
		musicDAO = factory.makeMusicDAO();	
	}
	
	@Override
	public List<Display> makeDisplayItem(String category) {
		List<Music> music = musicDAO.getMusicByCategory(category);
		List<Display> displayMusic = displaySetter.getMusicDisplayObjects(music);
		return displayMusic;
	}

	@Override
	public List<String> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
