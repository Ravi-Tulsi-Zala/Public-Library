package com.library.browsePage;

public class DisplayObjectInitializer {
	
	public IBrowseDisplayObjects getDisplayObject(String itemType) {
		IBrowseDisplayObjects browseDisplayObjects = null;
		IBrowseDisplayFactory browseFactory = BrowseDisplayFactory.getInstance();
		if (itemType.equals("Book")) {
			browseDisplayObjects = browseFactory.makeBookDisplay();
		} else if (itemType.equals("Movie")) {
			browseDisplayObjects = browseFactory.makeMovieDisplay();
		} else if (itemType.equals("Music")) {
			browseDisplayObjects = browseFactory.makeMusicDisplay();
		}
		return browseDisplayObjects;
	}
}
