package com.library.setterTest;

import org.junit.Test;

import com.library.BussinessModelSetter.DetailsSetter;
import com.library.businessModels.Music;

public class DetailSetterTest {

	@Test
	public void getMusicDetailsTest()
	{
		Music music = new Music();
		music.setArtist("a");
		music.setCategory("b");
		music.setDescription("c");	
		music.setRecordLabel("d");
		music.setItemID(1);
		music.setTitle("e");
		DetailsSetter detailSetter = new DetailsSetter();
		String details = detailSetter.getMusicDetails(music);
		System.out.print(details);
	}
}
