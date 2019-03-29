package com.library.businessModels;

import java.sql.SQLException;

import org.apache.tomcat.util.codec.binary.Base64;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.ICoverDAO;
import com.mysql.jdbc.Blob;

public class Display {
	
	private String title;
	private int itemID;
	private String image = null;
	private String itemType;
	private String details;
	private IDAOFactory factory = new DAOFactory();
	private ICoverDAO coverDAO = factory.makeCoverDAO();
	
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

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
		if(null==image)
		{
			setImage();
		}
		return image;
	}
	
	public void setImage() {
		Cover cover =  coverDAO.getCoverByID(itemID);
		Blob blob = (Blob) cover.getCoverBlob();
		try {
			if(blob!=null)
			{
				byte[] coverBytes = blob.getBytes(1,(int) blob.length());
				this.image = Base64.encodeBase64String(coverBytes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public String getItemType() {
		return itemType;
	}
	
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
