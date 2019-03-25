package com.library.localStorage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAO.BookDAO;
import com.library.DAO.CoverDAO;
import com.library.businessModels.Cover;

public class CoverImageLoader implements ICoverImageLoader {
	private static final Logger logger = LogManager.getLogger(CoverImageLoader.class);
	
	@Override
	public String loadCoverImageByItemIdToDisk(int itemId, String pathToDirToSaveInto) {
		String imagePath = null;
		byte [] bytes;
		CoverDAO coverDao = new CoverDAO();
		Cover cover = coverDao.getCoverByID(itemId);
		
		if(null == cover) {
			return null;
		}
		
		String imageName = itemId + "." + cover.getFileExtension();
		if (null != cover) {
			imagePath = pathToDirToSaveInto + File.separator + imageName;
			File file = new File(imagePath);
			BufferedOutputStream stream;
			try {
				bytes = cover.getCoverBlob().getBytes(1, (int) cover.getCoverBlob().length());
				stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
			} catch (SQLException e) {
				logger.log(Level.ALL, "Unable to read bytes from the image BLOB", e);
				return null;
			} catch (FileNotFoundException e) {
				logger.log(Level.ALL, "Unable to create image file " +  imagePath, e);
				return null;
			} catch (IOException e) {
				logger.log(Level.ALL, "Unable to write image byte stream to the disk. File  " +  imagePath, e);
				e.printStackTrace();
				return null;
			}
		}
		return imageName;
	}
}
