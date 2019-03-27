package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Music;
import com.library.controllers.LibraryFactorySingleton;

public class AddMusicController implements IAddMusicController {

	IDAOFactory factory;
	int itemIdOfMusic;
	IMusicDAO iMusicDAO;
	boolean isMusicCreated, isMusicCoverCreated;


	public AddMusicController() {
		factory = new DAOFactory();
		iMusicDAO = factory.makeMusicDAO();
	}

	public boolean addMusicRecordInDatabase(Music music, MultipartFile musicCoverImage) {

		itemIdOfMusic = iMusicDAO.createMusic(music);
		if (itemIdOfMusic == 0) {
			isMusicCreated = false;
		} else {
			isMusicCoverCreated = LibraryFactorySingleton.instance().getFactory().makeItemCoverSetter()
					.isCoverAddedToDatabase(itemIdOfMusic, musicCoverImage);
			isMusicCreated = true;
		}

		return (isMusicCreated && isMusicCoverCreated);

	}
}
