package com.library.dao;

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

import com.library.businessModels.UserItem;
import com.library.dbConnection.DatabaseConnection;

public class UserItemDAO implements IUserItemDAO {

	private PreparedStatement preparedStatement;
	private ResultSet resultSet = null;
	String query;
	Connection connection;
	private static final Logger logger = LogManager.getLogger(UserItemDAO.class);
	DatabaseConnection databaseConnection;

	public UserItemDAO() {

		try {
			databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
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
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				item = new UserItem();
				item.setCategory(resultSet.getString("Category"));
				item.setEmail(resultSet.getString("Email"));
				item.setTitle(resultSet.getString("Title"));
				item.setItemId(resultSet.getInt("Item_ID"));
				items.add(item);
			}
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {

			logger.log(Level.ALL, "Can not fetch outstanding borrowed items from db", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}
		return items;
	}

	@Override
	public boolean removeItem(UserItem item) {

		String email = item.getEmail();
		int itemId = item.getItemId();

		query = "DELETE from user_item WHERE Item_ID=? and Email=?";

		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete item from database", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}

		return false;
	}

	public boolean isItemBorrowed(UserItem item) {

		String email = item.getEmail();
		String title = item.getTitle();
		boolean isBorrowed = false;

		query = "SELECT * from user_item WHERE Email=? and Title=?";

		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isBorrowed = true;
			} else {
				isBorrowed = false;
			}
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not find item in User item table", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}

		return isBorrowed;

	}

	@Override
	public boolean addItemOnHold(UserItem item) {

		String email = item.getEmail();
		String category = item.getCategory();
		String title = item.getTitle();
		int itemId = item.getItemId();
		try {
			this.connection = databaseConnection.getConnection();
			query = "INSERT INTO holds (Email,Title,Category,Item_ID) VALUES (?,?, ?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, category);
			preparedStatement.setInt(4, itemId);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert movie into database", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}

		return false;

	}

	@Override
	public boolean isItemOnHold(int itemId) {

		boolean isItemOnHold = false;

		query = "SELECT * from holds WHERE Item_ID=?";

		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isItemOnHold = true;
			} else {
				isItemOnHold = false;
			}
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not find item in User item table", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}

		return isItemOnHold;

	}

	@Override
	public boolean addItem(UserItem item) {

		String email = item.getEmail();
		String category = item.getCategory();
		String title = item.getTitle();
		int itemId = item.getItemId();
		query = "INSERT INTO user_item (Item_ID,Email,Category,Title) VALUES (?,?, ?, ?)";

		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, itemId);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, category);
			preparedStatement.setString(4, title);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert movie into database", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}

		return false;
	}

	public UserItem getTheNextUserInLine(int itemId) {

		UserItem userOnHold = new UserItem();

		query = "SELECT * FROM holds WHERE Item_ID=? ORDER BY EntryDateTime LIMIT 1";
		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userOnHold.setCategory(resultSet.getString("Category"));
				userOnHold.setEmail(resultSet.getString("Email"));
				userOnHold.setTitle(resultSet.getString("Title"));
				userOnHold.setItemId(resultSet.getInt("Item_ID"));
			}
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {

			logger.log(Level.ALL, "Can not fetch outstanding borrowed items from db", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}
		return userOnHold;
	}

	@Override
	public void removeUserFromHold(UserItem userOnHold) {

		String email = userOnHold.getEmail();
		int itemId = userOnHold.getItemId();

		query = "DELETE from holds WHERE Item_ID=? and Email=?";

		try {
			this.connection = databaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete item from database", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);

		}

	}
}

