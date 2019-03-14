package com.library.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBSeachControllerBean {
	@Bean
	public DBSeachController getSingletoneDataBaseInstance() {
		return new DBSeachController();
	}
}
