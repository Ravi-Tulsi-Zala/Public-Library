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
import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.library.DAO.CoverDAO;
import com.library.businessModels.Cover;

public class CoverImageLoader implements ICoverImageLoader {
	private static final String SEPARATOR = File.separator;
	private static final String COVER_IMAGE_NOT_AVAILABLE_PATH = 
			SEPARATOR + "public" + SEPARATOR + "img" + SEPARATOR +"CoverImageNotAvailable.jpg";
	private static final String PATH_TO_DYNAMIC_CONTENT_DIR = 
			System.getProperty("user.dir") + SEPARATOR + "dynamicContent" + SEPARATOR;
	
	private static final Logger logger = LogManager.getLogger(CoverImageLoader.class);
	
	@Override
	public String loadCoverImageByItemIdToDisk(int itemId, String pathToDynamicContentSubDir) {
		String pathToDirToSaveInto = PATH_TO_DYNAMIC_CONTENT_DIR + pathToDynamicContentSubDir;
		String imagePath = null;
		byte [] bytes;
		CoverDAO coverDao = new CoverDAO();
		Cover cover = coverDao.getCoverByID(itemId);
		
		if(null == cover) {
			return COVER_IMAGE_NOT_AVAILABLE_PATH;
		}
		
		File dir = new File(pathToDirToSaveInto);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		String imageName = SEPARATOR + itemId + "." + cover.getFileExtension();
		String imageUrl = SEPARATOR + "dynamicContent" + SEPARATOR + pathToDynamicContentSubDir + imageName;
		imagePath = pathToDirToSaveInto + imageName;
		File file = new File(imagePath);
		BufferedOutputStream stream;
		try {
			bytes = cover.getCoverBlob().getBytes(1, (int) cover.getCoverBlob().length());
			stream = new BufferedOutputStream(new FileOutputStream(file));
			stream.write(bytes);
			stream.close();
		} catch (SQLException e) {
			logger.log(Level.ALL, "Unable to read bytes from the image BLOB", e);
			return COVER_IMAGE_NOT_AVAILABLE_PATH;
		} catch (FileNotFoundException e) {
			logger.log(Level.ALL, "Unable to create image file " +  imagePath, e);
			return COVER_IMAGE_NOT_AVAILABLE_PATH;
		} catch (IOException e) {
			logger.log(Level.ALL, "Unable to write image byte stream to the disk. File  " +  imagePath, e);
			e.printStackTrace();
			return COVER_IMAGE_NOT_AVAILABLE_PATH;
		}
		
		return imageUrl;
	}

	@Override
	public void deleteDynamicContent(String dynamicContentSubDirPath) {
		String dir = PATH_TO_DYNAMIC_CONTENT_DIR + dynamicContentSubDirPath;
		
		try {
			FileUtils.deleteDirectory(new File(dir));
		} catch (IOException e) {
			logger.log(Level.ALL, "Deletion of the directory was unsuccessful: " + dir, e);
		} catch (IllegalArgumentException e){
			logger.log(Level.ALL, "Was not able to delete a directory. Directory does not exist or is not a directory: " + dir , e);
		}	
	}
}
