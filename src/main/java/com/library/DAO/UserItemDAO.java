package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.IDAO.IUserItemDAO;
import com.library.dbConnection.DatabaseConnection;

public class UserItemDAO implements IUserItemDAO{
	Connection connection;
	private PreparedStatement preparedStatement;
	String query;
	
	public UserItemDAO() {
		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Boolean checkPassword(String emailAddress,String Password) {
		query = "SELECT Password from user_info WHERE Email = ?";
		ResultSet result;
		try {
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, emailAddress);
			result = preparedStatement.executeQuery();
			if(!result.next())
			{
				return false;
			}
			String databasePassword = result.getString("Password");
			Boolean doesPasswordMatch = databasePassword.equals(Password);
			return  doesPasswordMatch;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean changePassword(String emailAddress, String password) {
		 query = "UPDATE user_info SET Password = ? WHERE Email = ?";
		 try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1, password);
			 preparedStatement.setString(2, emailAddress);
			 preparedStatement.executeUpdate();
			 return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return false;
	}
}
