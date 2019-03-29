package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Music;
import com.library.messages.Messages;
import com.library.routes.LibraryFactorySingleton;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryControllerFactory;

public class AddMusicController implements IAddMusicController {

	IDAOFactory factory;
	int itemIdOfMusic;
	IMusicDAO iMusicDAO;
	boolean isMusicCreated, isMusicCoverCreated, isMusicDuplicate;;

	public AddMusicController() {
		factory = new DAOFactory();
		iMusicDAO = factory.makeMusicDAO();
		ILibraryFactory iLibraryfactory = new LibraryControllerFactory();
		LibraryFactorySingleton.instance().build(iLibraryfactory);
	}

	public Messages addMusicRecordInDatabase(Music music, MultipartFile musicCoverImage) {

		isMusicDuplicate = iMusicDAO.checkMusicDuplicacy(music);
		
		if(isMusicDuplicate)
		{
			return Messages.ERROR_DUPLICATE_MUSIC;
		}
				
		itemIdOfMusic = iMusicDAO.createMusic(music);
		if (itemIdOfMusic == 0) {
			
			return Messages.ERROR_MUSIC_CAN_NOT_BE_CREATED;
			
		} else {
			isMusicCoverCreated = LibraryFactorySingleton.instance().getFactory().makeItemCoverSetter()
					.isCoverAddedToDatabase(itemIdOfMusic, musicCoverImage);
			if(isMusicCoverCreated)
			{
				return Messages.SUCCESS_MUSIC;
			}
			else
			{
				return Messages.ERROR_MUSIC_CAN_NOT_BE_CREATED;
			}
		}


	}
}
