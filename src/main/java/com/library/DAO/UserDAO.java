package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.library.IDAO.IUserDAO;
import com.library.businessModels.User;
import com.library.dbConnection.DatabaseConnection;

public class UserDAO implements IUserDAO {

	Connection connection;
	private PreparedStatement preparedStatement;
	String query;
	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	public UserDAO() {
		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			logger.log(Level.ALL, "Unable to connect to database", e);
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
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch password from user info", e);
		}
		return false;
	}

	@Override
	public String getEmailRelatedPassword(String emailAddress) {
		query = "SELECT Password from user_info WHERE Email = ?";
		ResultSet result;
		String databasePassword="";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailAddress);
			result = preparedStatement.executeQuery();
			databasePassword = result.getString("Password");
			return databasePassword;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch password from user info", e);
		}
		return databasePassword;
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
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update password for the specific user emailid", e);
		}
		return false;
	}

	@Override
	public Boolean registerUser(User user) {
		query = "INSERT INTO user_info (User_name,Phone_Number,Email,Password) VALUES (?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getFullName());
			preparedStatement.setString(2, user.getPhoneNumber());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert a record in user info table", e);
		}
		return false;
	}
}
