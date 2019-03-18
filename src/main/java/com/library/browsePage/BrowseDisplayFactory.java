package com.library.browsePage;

public class BrowseDisplayFactory implements IBrowseDisplayFactory{

	@Override
	public IBrowseDisplayObjects makeBookDisplay() {
		return new BrowseBooks();
	}

	@Override
	public IBrowseDisplayObjects makeMovieDisplay() {
		return new BrowseMovies();
	}

	@Override
	public IBrowseDisplayObjects makeMusicDisplay() {
		return new BrowseMusic();
	}

}
