package com.library.itemDescription;

import com.library.DAOFactory.DAOFactory;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Display;
import com.library.businessModels.Music;

public class MusicDescription implements IDescription{
	
	Music music;
	String cover;
	
	public MusicDescription(Display display) {
		DAOFactory factory = new DAOFactory();
		IMusicDAO musicDAO = factory.makeMusicDAO();
		int itemID = display.getItemID();
		music = musicDAO.getMusicById(itemID);
		cover = display.getImage();
	}

	public String getCover() 
	{
		return cover;
	}
	
	public String getDescription()
	{
		return null;
	}
	
}
