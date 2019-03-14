package com.library.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseBean {
	@Bean
	public DataBase getSingletoneDataBaseInstance() {
		return DataBase.instance();
	}
}
