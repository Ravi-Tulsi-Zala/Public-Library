package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.IDAO.IUserDAO;
import com.library.businessModels.User;
import com.library.dbConnection.DatabaseConnection;

public class UserDAO implements IUserDAO {

	Connection connection;
	private PreparedStatement preparedStatement;
	String query;

	public UserDAO() {
		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean checkPassword(String emailAddress, String Password) {
		query = "SELECT Password from user_info WHERE Email = ?";
		ResultSet result;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailAddress);
			result = preparedStatement.executeQuery();
			if (!result.next()) {
				return false;
			}
			String databasePassword = result.getString("Password");
			Boolean doesPasswordMatch = databasePassword.equals(Password);
			return doesPasswordMatch;
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean registerUser(User user) {

		query = "INSERT INTO user_info (User_name,Phone_Number,Email,Password,Status) VALUES (?,?,?,?.?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getFullName());
			preparedStatement.setString(2, user.getPhoneNumber());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, "Inactive");
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean isUserActive(String emailAddress) {
		query = "SELECT Status from user_info WHERE Email = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailAddress);
			ResultSet result = preparedStatement.executeQuery();
			if (!result.next()) {
				return false;
			}
			String userStatus = result.getString("Status");
			Boolean isUserActive = userStatus.equals("Active");
			return isUserActive;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean toggleStatus(String emailAddress) {

		try {

			if (isUserActive(emailAddress)) {
				query = "UPDATE user_info SET Status = ? WHERE Email=?";

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "Active");
				preparedStatement.setString(2, emailAddress);
				preparedStatement.executeUpdate();
				return true;

			} else {
				query = "UPDATE user_info SET Status = ? WHERE Email=?";

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "Inactive");
				preparedStatement.setString(2, emailAddress);
				preparedStatement.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
