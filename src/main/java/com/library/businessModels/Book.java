package com.library.businessModels;

public class Book extends LibraryItem {
	
	private String author;
	private int isbn;
	private String publisher;
	private String description;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getISBN() {
		return isbn;
	}
	public void setISBN(int isbn) {
		this.isbn = isbn;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
