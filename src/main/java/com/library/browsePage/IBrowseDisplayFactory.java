package com.library.browsePage;

public interface IBrowseDisplayFactory {
	public IBrowseDisplayComponent makeBookDisplay();
	public IBrowseDisplayComponent makeMovieDisplay();
	public IBrowseDisplayComponent makeMusicDisplay();
}
