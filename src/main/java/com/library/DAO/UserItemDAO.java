package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.IDAO.IUserItemDAO;
import com.library.businessModels.UserItem;
import com.library.dbConnection.DatabaseConnection;

public class UserItemDAO implements IUserItemDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	private static final Logger logger = LogManager.getLogger(UserItemDAO.class);

	public UserItemDAO() {

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {

			logger.log(Level.ALL, "Unable to connect to database", e);
		}

	}

	@Override
	public List<UserItem> getAllBorrowedItems() {

		UserItem item;
		query = "SELECT * FROM user_item";
		List<UserItem> items = new ArrayList<UserItem>();

		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				item = new UserItem();
				item.setCategory(resultSet.getString("Category"));
				item.setEmail(resultSet.getString("Email"));
				item.setTitle(resultSet.getString("Title"));
				items.add(item);
			}
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {

			logger.log(Level.ALL, "Can not fetch outstanding borrowed items from db", e);
		}
		return items;
	}

	@Override
	public boolean removeItem(UserItem item) {

		String email = item.getEmail();
		String title = item.getTitle();

		query = "DELETE from user_item WHERE Email=? and Title=?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete item from database", e);
		}

		return false;
	}

	public boolean isItemBorrowed(UserItem item) {

		String email = item.getEmail();
		String title = item.getTitle();
		boolean isBorrowed = false;
		
		query = "SELECT from user_item WHERE Email=? and Title=?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isBorrowed = true;
			} else {
				isBorrowed  = false;
			}
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not find item in User item", e);
		}

		return isBorrowed;
		
	}

	public boolean isItemOnHold(UserItem item) {

		return false;
	}

	@Override
	public boolean addItem(UserItem item) {

		String email = item.getEmail();
		String category = item.getCategory();
		String title = item.getTitle();

		try {
			query = "INSERT INTO user_item (Email,Category,Title) VALUES (?, ?, ?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, category);
			preparedStatement.setString(3, title);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert movie into database", e);
		}

		return false;
	}

}
