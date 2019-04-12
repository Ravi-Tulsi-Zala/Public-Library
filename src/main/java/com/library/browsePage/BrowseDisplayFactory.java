package com.library.browsePage;

public class BrowseDisplayFactory implements IBrowseDisplayFactory{

	private static BrowseDisplayFactory browseDisplayFactory = null;
	
	public static BrowseDisplayFactory getInstance()
	{
		if(browseDisplayFactory == null)
		{
			browseDisplayFactory = new BrowseDisplayFactory();
		}
		return browseDisplayFactory;
	}
	
	@Override
	public IBrowseDisplayComponent makeBookDisplay() {
		return new BrowseBooks();
	}

	@Override
	public IBrowseDisplayComponent makeMovieDisplay() {
		return new BrowseMovies();
	}

	@Override
	public IBrowseDisplayComponent makeMusicDisplay() {
		return new BrowseMusic();
	}

}
