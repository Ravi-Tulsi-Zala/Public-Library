package com.library.dbConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	
public class DatabaseConnection 
{
	private static DatabaseConnection databaseConnectionInstance = null;
	private Connection connection = null;
	
	private DatabaseConnection() {
		
		DatabaseCredential databaseCredential = new DatabaseCredential();
		String databaseName = databaseCredential.getDatabaseName();
		String username = databaseCredential.getUserName();
		String password = databaseCredential.getPassword();
		String serverName = databaseCredential.getServerName();
		String databaseURL = "jdbc:mysql://" + serverName+"/"+databaseName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(databaseURL,username,password);
		} catch (ClassNotFoundException e) {
			
	        System.out.println("JDBC driver not found");
	        e.printStackTrace();
	    } catch (SQLException e)
		{
	    	System.out.println("Connection Failed");
		}	
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
	
	public static DatabaseConnection getDatabaseConnectionInstance()
	{
		if(null==databaseConnectionInstance)
		{
			databaseConnectionInstance = new DatabaseConnection();
		}
		return databaseConnectionInstance;
	}
}


