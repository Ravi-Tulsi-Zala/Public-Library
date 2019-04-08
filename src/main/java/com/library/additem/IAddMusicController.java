package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Music;

public interface IAddMusicController {

	public AddItemMessagesEnum addMusicRecordInDatabase(Music music, MultipartFile coverImage );
	
}
