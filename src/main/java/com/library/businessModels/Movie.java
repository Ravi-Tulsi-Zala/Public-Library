package com.library.businessModels;

public class Movie extends LibraryItem {
	
	private String director;
	private String description;
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
