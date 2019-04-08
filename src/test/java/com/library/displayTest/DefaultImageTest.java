package com.library.displayTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.library.localStorage.DefaultImageFetcher;

public class DefaultImageTest {

	@Test
	public void testDefaultImage()
	{
		DefaultImageFetcher defaultImageFetcher = new DefaultImageFetcher();
		String image = defaultImageFetcher.getDefaultCover();
		String expectedImage = "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";
		assertEquals(expectedImage, image);
	}
}