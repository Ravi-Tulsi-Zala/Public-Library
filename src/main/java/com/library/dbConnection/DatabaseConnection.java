package com.library.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection databaseConnectionInstance = null;
	private Connection connection = null;

	public Connection getConnection() {
		
		DatabaseCredentials databaseCredentials = new DatabaseCredentials();
		String databaseName = databaseCredentials.getDatabaseName();
		String username = databaseCredentials.getUserName();
		String password = databaseCredentials.getPassword();
		String serverName = databaseCredentials.getServerName();
		String databaseURL = "jdbc:mysql://" + serverName + "/" + databaseName;
		try {
			if(connection.isClosed()==true)
			{
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection(databaseURL, username, password);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.connection;
	}

	public static DatabaseConnection getDatabaseConnectionInstance() {
		if (null == databaseConnectionInstance) {
			databaseConnectionInstance = new DatabaseConnection();
		}
		return databaseConnectionInstance;
	}
	
	public static Connection getConn()
	{
		return getDatabaseConnectionInstance().connection;
	}
	
}
