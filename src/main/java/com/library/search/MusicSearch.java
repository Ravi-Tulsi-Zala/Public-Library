package com.library.search;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.LibraryItem;

public class MusicSearch extends SearchCategory {

	private boolean searchInMusic = true;
	private boolean searchMusicAlbumName = true;
	private boolean searchMusicArtist = true;
	private boolean searchMusicRecordLabel = true;
	
	@Override
	public List<LibraryItem> search(String searchterms) {
		return daoFactory.makeMusicDAO().getMusicBySearchTerms(this, searchterms);
	}
	
	@Override
	public boolean equals(SearchCategory previousMusicSearch) {
		MusicSearch prev = (MusicSearch) previousMusicSearch;
		boolean isEqual = 
				this.searchInMusic == prev.searchInMusic &&
				this.searchMusicAlbumName == prev.searchMusicAlbumName &&
				this.searchMusicArtist == prev.searchMusicArtist &&
				this.searchMusicRecordLabel == prev.searchMusicRecordLabel;
		
		return isEqual;
	}

	public boolean isSearchInMusic() {
		return searchInMusic;
	}

	public void setSearchInMusic(boolean searchInMusic) {
		this.searchInMusic = searchInMusic;
	}

	public boolean isSearchMusicAlbumName() {
		return searchMusicAlbumName;
	}

	public void setSearchMusicAlbumName(boolean searchMusicAlbumName) {
		this.searchMusicAlbumName = searchMusicAlbumName;
	}

	public boolean isSearchMusicArtist() {
		return searchMusicArtist;
	}

	public void setSearchMusicArtist(boolean searchMusicArtist) {
		this.searchMusicArtist = searchMusicArtist;
	}

	public boolean isSearchMusicRecordLabel() {
		return searchMusicRecordLabel;
	}

	public void setSearchMusicRecordLabel(boolean searchMusicRecordLabel) {
		this.searchMusicRecordLabel = searchMusicRecordLabel;
	}
}
