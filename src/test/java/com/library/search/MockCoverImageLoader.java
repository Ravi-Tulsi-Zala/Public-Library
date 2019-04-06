package com.library.search;

import java.io.File;

import com.library.localStorage.CoverImageLoader;

public class MockCoverImageLoader extends CoverImageLoader {
	private static final String PATH_TO_DYNAMIC_CONTENT_DIR = 
			System.getProperty("user.dir") + File.separator + "dynamicContent" + File.separator;
	public String loadCoverImageByItemIdToDisk(int itemId, String pathToDynamicContentSubDir) {
		return PATH_TO_DYNAMIC_CONTENT_DIR + pathToDynamicContentSubDir + File.separator + itemId;
	}
}
