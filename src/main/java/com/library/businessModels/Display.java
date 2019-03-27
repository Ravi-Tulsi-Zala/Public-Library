package com.library.businessModels;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.tomcat.util.codec.binary.Base64;

import com.mysql.jdbc.Blob;

public class Display {
	private String title;
	private int itemID;
	private String image = null;
	private String itemType;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(Blob blob) {
		
		
		try {
			if(blob!=null)
			{
				byte[] coverBytes = blob.getBytes(1,(int) blob.length());
				this.image = Base64.encodeBase64String(coverBytes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		if(this.image==null)
		{
			try {
					String fileName = "defaultImage.png";
					File file = new File(fileName);
				 	FileInputStream fileInputStreamReader = new FileInputStream(file);
		            byte[] bytes = new byte[(int)file.length()];
		            fileInputStreamReader.read(bytes);
		            this.image = Base64.encodeBase64String(bytes);
		            fileInputStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
