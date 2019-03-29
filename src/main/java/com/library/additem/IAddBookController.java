package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Book;
import com.library.messages.Messages;

public interface IAddBookController {
	
	public Messages addBookRecordInDatabase(Book book, MultipartFile coverImage);
}
