package com.library.DatabaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.library.BusinessModels.Music;
import com.library.DAO.MusicDAO;
import com.library.IDAO.IMusicDAO;

public class MusicTest {

	IMusicDAO iMusicDAO = new MusicDAO();

	@Test
	public void getMusicByIdTest() {
		Music music = iMusicDAO.getMusicById(3001);
		assertEquals("Despacito", music.getTitle());
	}

	@Test
	public void getMusicByTitleTest() {
		List<Music> listOfMusicByTitle = iMusicDAO.getMusicByTitle("Despacito");
		assertEquals("Luis Fonsi", listOfMusicByTitle.get(0).getArtist());
	}

	@Test
	public void getMusicsByDirectorNameTest() {
		List<Music> listOfMusicsByDirectorName = iMusicDAO.getMusicByArtistName("Linkin Park");
		assertEquals("Numb", listOfMusicsByDirectorName.get(0).getTitle());

	}

	@Test
	public void getMusicByCategoryTest() {
		List<Music> listOfMusicsByCategory = iMusicDAO.getMusicByCategory("Pop");
		assertEquals("Despacito", listOfMusicsByCategory.get(0).getTitle());
		assertEquals("Thunder", listOfMusicsByCategory.get(1).getTitle());

	}

	@Test
	public void getMusicsByRecordLabelTest() {
		List<Music> listOfMusicsByRecordLabel = iMusicDAO.getMusicByRecordLabel("Interscope");
		assertEquals("Thunder", listOfMusicsByRecordLabel.get(0).getTitle());
	}

	@Test
	public void createMusicTest() {
		Music music = new Music();
		music.setCategory("Rock");
		music.setRecordLabel("Capitol");
		music.setTitle("Scientist");
		music.setArtist("Coldplay");
		music.setAvailability(6);
		assertTrue(iMusicDAO.createMusic(music));
	}

	@Test
	public void updateMusicTest() {
		Music music = new Music();
		music.setItemID(3004);
		music.setCategory("House");
		music.setRecordLabel("Virgin");
		music.setTitle("Titanium");
		music.setArtist("Sia");
		music.setAvailability(7);
		assertTrue(iMusicDAO.updateMusic(music));
	}

	@Test
	public void deleteMusicTest() {
		Music music = new Music();
		music.setItemID(3005);
		assertTrue(iMusicDAO.deleteMusic(music));
	}
}
