package com.library.additem;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
=======

import com.library.dao.ICoverDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
>>>>>>> ec578c5e1375f0ffad7bf01e4611a7aa35a7a6bf

import com.library.dao.ICoverDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
public class ItemCoverSetter implements IItemCoverSetter {

	IDAOFactory iDAOfactory;
	private boolean isCoverCreated;
	ICoverDAO coverDAO;
	private static final Logger logger = LogManager.getLogger(ItemCoverSetter.class);
	
	public ItemCoverSetter() {
		iDAOfactory = new DAOFactory();
		coverDAO = iDAOfactory.makeCoverDAO();
	}
	
	public boolean isCoverAddedToDatabase(int itemId,MultipartFile coverImage)
	{
		String originalFileName = coverImage.getOriginalFilename();
		

		try {
			byte[] bytes;
			try {
				bytes = coverImage.getBytes();
				Blob coverBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
				String[] fileNameTokens = coverImage.getOriginalFilename().split("\\.");
				String fileExtension = fileNameTokens[fileNameTokens.length - 1];
				isCoverCreated = coverDAO.createCoverByID(itemId, coverBlob, fileExtension);
			} catch (IOException e) {
				logger.log(Level.ALL,"Error in creating cover",e);
			}
			
		} catch (SQLException e) {
			logger.log(Level.ALL,"Error in creating cover",e);
		}
		return isCoverCreated;
	}
	
}
