package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Book;

public interface IAddBookController {
	
	public boolean addBookRecordInDatabase(Book book, MultipartFile coverImage);
}
