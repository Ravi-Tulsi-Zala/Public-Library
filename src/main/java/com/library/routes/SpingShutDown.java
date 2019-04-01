package com.library.routes;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.library.dbConnection.DatabaseConnection;

@Component
public class SpingShutDown {
	
	@PreDestroy
    public void destroy() {
		DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
		databaseConnection.closeConnection();
    }
}

