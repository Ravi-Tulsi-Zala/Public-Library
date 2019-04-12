package com.library.localStorage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;

public class DefaultImageFetcher {

	private String image = null;
	private String imagePath;
	private String imageType;
	private static final Logger logger = LogManager.getLogger(DefaultImageFetcher.class);
	
	
	public DefaultImageFetcher() {
		imagePath = "CoverImageNotAvailable.jpg";
		imageType = "jpg";
	}
	
	public String getDefaultCover()
	{
		try {
			File imageFile = new ClassPathResource(imagePath).getFile();
			BufferedImage defaultCover = ImageIO.read(imageFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(defaultCover, imageType, bos);
			byte[] imageBytes = bos.toByteArray();
			image = Base64.encodeBase64String(imageBytes);
            bos.close();
		} 
		catch (IOException e) {
			
			logger.log(Level.ALL,"Default image not found",e);
		}
		return image;
	}
}

