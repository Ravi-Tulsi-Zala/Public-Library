package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.BusinessModels.User;
import com.library.IDAO.IUserDAO;
import com.library.dbConnection.DatabaseConnection;

public class UserDAO implements IUserDAO {
	
	Connection connection;
	private PreparedStatement preparedStatement;
	String query;
	
	public UserDAO() {
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
		query = "select password from user_info where Email = '" + emailAddress + "'";
		ResultSet result;
		try {
			preparedStatement  = connection.prepareStatement(query);
			result = preparedStatement.executeQuery(query);
			if(!result.next())
			{
				return false;
			}
			String databasePassword = result.getString("Password");
			return  databasePassword.equals(Password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean changePassword(String emailAddress, String password) {
		 query = "update user_info set password ='" + password + "' where Email='" + emailAddress + "'";
		 try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.executeUpdate(query);
			 return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return false;
	}

	@Override
	public Boolean registerUser(User user) {
		
		query = "Insert into user_info (User_name,Phone_Number,Email,Password,Status) values ('" + user.getFullName() 
				+ "'," + user.getPhoneNumber() + ",'" +  user.getEmailAddress() + "','" + user.getPassword() + "','Inactive')";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.executeUpdate(query);
			 return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return false;
	}

	@Override
	public Boolean isUserActive(String emailAddress) {
		query = "Select status from user_info where Email = '" + emailAddress + "'";
		try {
			preparedStatement  = connection.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery(query);
			if(!result.next())
			{
				return false;
			}
			String userStatus = result.getString("Status");
			return  userStatus.equals("Active");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean toggleStatus(String emailAddress) {
		if(isUserActive(emailAddress))
		{
			query = "update user_info set Status = 'Inactive' where Email='" + emailAddress + "'";
		}
		else
		{
			query = "update user_info set Status = 'Active' where Email='" + emailAddress + "'";
		}
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.executeUpdate(query);
			 return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return false;
	}

}
