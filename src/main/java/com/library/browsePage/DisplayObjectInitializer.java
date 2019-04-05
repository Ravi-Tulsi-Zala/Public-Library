package com.library.browsePage;

public class DisplayObjectInitializer {
	
	private static String book = "Book";
	private static String music = "Music";
	private static String movie = "Movie";
	
	public IBrowseDisplayObjects getDisplayObject(String itemType) {
		IBrowseDisplayObjects browseDisplayObjects = null;
		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
		if (itemType.equals(book)) {
			browseDisplayObjects = browseFactory.makeBookDisplay();
		} else if (itemType.equals(music)) {
			browseDisplayObjects = browseFactory.makeMovieDisplay();
		} else if (itemType.equals(movie)) {
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		}
		return browseDisplayObjects;
	}
}
