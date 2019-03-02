package com.library.DAOMapper;

import java.sql.ResultSet;

import com.library.POJO.Book;

public class BookMapper {
	
	public Book mapBook(ResultSet resultSet)
	{
		try {
			Book book = new Book();
			book.setISBN(resultSet.getInt("ISBN"));
			book.setItemID(resultSet.getInt("Item_ID"));
			book.setTitle(resultSet.getString("Title"));
			book.setAuthor(resultSet.getString("Author"));
			book.setCategory(resultSet.getString("Category"));
			book.setDescription(resultSet.getString("Description"));
			book.setPublisher(resultSet.getString("Publisher"));
			return book;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void sayHello() {
		System.out.print("Hello");
	}
}
