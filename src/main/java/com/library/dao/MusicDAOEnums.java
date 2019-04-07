package com.library.dao;

public enum MusicDAOEnums {

	QUERY_GET_MUSIC_BY_ID("SELECT * FROM books WHERE Item_ID = ?"),
	QUERY_GET_MUSICS_BY_CATEGORY("SELECT * FROM books WHERE Category=?"),
	QUERY_GET_MUSIC_CATEGORIES("SELECT Distinct Category from books"),
	QUERY_DELETE_MUSIC_BY_ID("Delete FROM books WHERE Item_ID = ?"),
	QUERY_INSERT_MUSIC("Insert into books (Category,Title,Author,ISBN,Publisher,Description,Availability) Values (?,?,?,?,?,?,?)"),
	QUERY_IS_DUPLICATE_MUSIC("SELECT * FROM books where Title=? and Author=?"),
	QUERY_INCREASE_MUSIC_COUNT("update books set count = count + 1 where Item_ID = ?"),
	QUERY_UPDATE_AVAILABILITY("update books set Availability = ? where Item_ID = ?"),
	QUERY_UPDATE_MUSIC("Update books  set Category = ?, Title = ?, Author = ?, ISBN =  ?, Publisher = ?, Description = ?, Availability = ? WHERE Item_ID = ?"),
	QUERY_GET_CURRENT_AVAILABILITY_OF_MUSIC("Select Availability from books where Item_ID = ?");
	
	String query;

	private MusicDAOEnums(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}
	
}
