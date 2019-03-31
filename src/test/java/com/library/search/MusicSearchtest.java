package com.library.search;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MusicSearchtest {
	private MusicSearch ms;
	
	@Before
	public void setUp() throws Exception {
		ms = new MusicSearch();
	}
	
	@Test
	public void allDataMembersDefaultValuesAreTrue() {
		assertTrue(ms.isSearchInMusic());
		assertTrue(ms.isSearchMusicAlbumName());
		assertTrue(ms.isSearchMusicArtist());
		assertTrue(ms.isSearchMusicRecordLabel());
	}
	@Test
	public void canSetAndGetAllDataMembers() {
		ms.setSearchInMusic(false);
		assertFalse(ms.isSearchInMusic());
		ms.setSearchMusicAlbumName(false);
		assertFalse(ms.isSearchMusicAlbumName());
		ms.setSearchMusicArtist(false);
		assertFalse(ms.isSearchMusicArtist());
		ms.setSearchMusicRecordLabel(false);
		assertFalse(ms.isSearchMusicRecordLabel());
	}
}
