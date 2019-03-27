package com.library.DisplayTest;

import java.sql.Blob;

import org.junit.Test;

import com.library.DAO.CoverDAO;
import com.library.businessModels.Cover;
import com.library.businessModels.Display;

public class TP {

	@Test
	public void checkDisplayImage()
	{
		CoverDAO coverDAO = new CoverDAO();
		Cover cover = coverDAO.getCoverByID(3001); 
		Blob blob = cover.getCoverBlob();
		Display display = new Display();
		display.setImage((com.mysql.jdbc.Blob) blob);
		System.out.print(display.getImage());
	}
}
