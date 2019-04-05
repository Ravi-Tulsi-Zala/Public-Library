package com.library.setterTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.library.businessModelSetter.DetailedDisplaySetter;
import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.Music;

public class DisplayDetailSetterTest {

	DetailedDisplaySetter detailedDisplaySetter = new DetailedDisplaySetter();
	
	@Test
	public void makeDetailedMusicTest()
	{
		Music music = new Music();
		music.setArtist("a");
		music.setCategory("b");
		music.setDescription("c");	
		music.setRecordLabel("d");
		music.setItemID(1);
		music.setTitle("e");
		DisplayDetailed displayDetailed = detailedDisplaySetter.makeDetailedMusic(music);
		assertEquals(1, displayDetailed.getItemID());
		assertEquals("e", displayDetailed.getTitle());
		assertEquals("Artist: a\n" + 
				"Record Label: d\n", displayDetailed.getDetails());
	}
}
