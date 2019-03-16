package com.library.DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.IDAO.ICoverBlobDAO;
import com.library.dbConnection.DatabaseConnection;

public class CoverBlobDAO implements ICoverBlobDAO {
	
	private final int INDEX_OF_BLOB_COLUMN_IN_RESULT_SET = 0;
	private final String SELECT_COVER_BY_ITEM_ID_QUERY = "SELECT Cover FROM cover WHERE Item_ID = ?";
	private final String INSERT_COVER_BY_ITEM_ID_QUERY = "INSERT INTO cover (Item_ID, Cover) Values (?,?)";
	private final String DELETE_COVER_BY_ITEM_ID_QUERY = "DELETE FROM cover WHERE Item_ID = ?";
	private PreparedStatement preparedStatement;
	private Connection dbConnection;
	
	public CoverBlobDAO() {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.dbConnection = databaseConnection.getConnection();
	}
	
	@Override
	public Blob getCoverBlobByID(int itemID) {		
		try {
			preparedStatement = dbConnection.prepareStatement(SELECT_COVER_BY_ITEM_ID_QUERY);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getBlob(INDEX_OF_BLOB_COLUMN_IN_RESULT_SET);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public boolean setCoverBlobByID(int itemID, Blob coverBlob) {
		try {
			preparedStatement = dbConnection.prepareStatement(INSERT_COVER_BY_ITEM_ID_QUERY);
			preparedStatement.setInt(1, itemID);
			preparedStatement.setBlob(2, coverBlob);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteCoverBlobByID(int itemID) {
		try {
			preparedStatement = dbConnection.prepareStatement(DELETE_COVER_BY_ITEM_ID_QUERY);
			preparedStatement.setInt(1, itemID);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
}

