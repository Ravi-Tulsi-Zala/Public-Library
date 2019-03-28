package com.library.businessModels;

import org.springframework.web.multipart.MultipartFile;

public class CoverImage {
	
	private MultipartFile coverImage;
	
	public MultipartFile getCoverImage() {
		return coverImage;
	}
	
	 public void setCoverImage(MultipartFile coverImage) {
		this.coverImage = coverImage;
	}

}
