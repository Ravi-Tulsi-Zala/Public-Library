package com.library.browsePage;

public interface IDisplayFactory {
	public IDisplayObjects makeBookDisplay();
	public IDisplayObjects makeMovieDisplay();
	public IDisplayObjects makeMusicDisplay();
}
