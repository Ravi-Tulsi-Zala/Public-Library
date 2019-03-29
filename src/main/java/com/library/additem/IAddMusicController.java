package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Music;
import com.library.messages.Messages;

public interface IAddMusicController {

	public Messages addMusicRecordInDatabase(Music music, MultipartFile coverImage );
	
}
