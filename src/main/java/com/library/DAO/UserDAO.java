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
		query = "select password from user_info where Email = ?";
		ResultSet result;
		try {
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, emailAddress);
			result = preparedStatement.executeQuery(query);
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
		 query = "update user_info set password = ? where Email= ?";
		 try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1, password);
			 preparedStatement.setString(2, emailAddress);
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
		
		query = "Insert into user_info (User_name,Phone_Number,Email,Password,Status) "
				+ "values (?,?,?,?.?)";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1,user.getFullName());
			 preparedStatement.setInt(2, user.getPhoneNumber());
			 preparedStatement.setString(3, user.getEmailAddress());
			 preparedStatement.setString(4, user.getPassword());
			 preparedStatement.setString(5, "Inactive");
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
		query = "Select status from user_info where Email = ?";
		try {
			preparedStatement  = connection.prepareStatement(query);
			preparedStatement.setString(1, emailAddress);
			ResultSet result = preparedStatement.executeQuery(query);
			if(!result.next())
			{
				return false;
			}
			String userStatus = result.getString("Status");
			Boolean isUserActive = userStatus.equals("Active");
			return  isUserActive;
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
			query = "update user_info set Status = 'Inactive' where Email=?";
		}
		else
		{
			query = "update user_info set Status = 'Active' where Email=?";
		}
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1, emailAddress);
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
