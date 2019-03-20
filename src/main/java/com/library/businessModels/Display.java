package com.library.businessModels;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import com.mysql.jdbc.Blob;

public class Display {
	private String title;
	private int itemID;
	private Image image = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Blob blob) {
		try {
			InputStream imageStream = blob.getBinaryStream();
			Image image = ImageIO.read(imageStream);
			this.image = image;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(this.image==null)
		{
			try {
				this.image = ImageIO.read(new File("defaultCoverImage.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
