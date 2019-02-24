package com.library.DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;

import com.library.DAO.IUserDAO;
import com.library.POJO.User;
import com.library.dbConnection.DatabaseConnection;

public class UserDAOImpl implements IUserDAO {
	
	java.sql.Statement stm;
	String query;
	
	public UserDAOImpl() {
		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			Connection connection = databaseConnection.getConnection();
			stm = connection.createStatement();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String getPassword(String emailAddress) {
		query = "select password from user_info where Email = '" + emailAddress + "'";
		ResultSet result;
		try {
			result = stm.executeQuery(query);
			return result.getString("Password");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changePassword(String emailAddress, String password) {
		
	}

	@Override
	public void registerUser(User user) {
		
	}

}
