package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.BussinessModelSetter.IMusicSetter;
import com.library.BussinessModelSetter.MusicSetter;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Music;
import com.library.dbConnection.DatabaseConnection;
import com.library.search.MusicSearch;

public class MusicDAO implements IMusicDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	IMusicSetter musicSetter = new MusicSetter();
	private static final Logger logger = LogManager.getLogger(MusicDAO.class);
	ResultSet resultSet;
	DatabaseConnection databaseConnection;

	public MusicDAO() {

		try {
			databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();

		} catch (Exception e) {
			logger.log(Level.ALL, "Unable to connect to database", e);
		}
	}

	@Override
	public Music getMusicById(int itemID) {

		Music music = new Music();
		this.connection = databaseConnection.getConnection();
		List<Music> musics = new ArrayList<>();
		query = "SELECT * from music WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			resultSet = preparedStatement.executeQuery();
			musics = musicSetter.mapMusic(resultSet);
			music = musics.get(0);
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch music using id", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return music;
	}

	@Override
	public List<Music> getMusicByCategory(String category) {

		this.connection = databaseConnection.getConnection();
		query = "SELECT * from music WHERE Category LIKE ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + category + "%");
			resultSet = preparedStatement.executeQuery();
			musicsByCategory = musicSetter.mapMusic(resultSet);
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch the list of music by the specific category", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return musicsByCategory;

	}

	@Override
	public int createMusic(Music music) {

		this.connection = databaseConnection.getConnection();
		String musicCategory = music.getCategory();
		String musicTitle = music.getTitle();
		String musicArtist = music.getArtist();
		String musicRecordLabel = music.getRecordLabel();
		int musicAvailability = music.getAvailability();
		int recentlyAddedMusicId = 0;

		try {
			query = "INSERT INTO music (Category,Title,Artist,Record_Label,Availability) VALUES ( ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, musicCategory);
			preparedStatement.setString(2, musicTitle);
			preparedStatement.setString(3, musicArtist);
			preparedStatement.setString(4, musicRecordLabel);
			preparedStatement.setInt(5, musicAvailability);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				recentlyAddedMusicId = resultSet.getInt(1);
			}

			return recentlyAddedMusicId;

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not create music entry in database", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return recentlyAddedMusicId;
	}

	@Override
	public Boolean updateMusic(Music music) {

		this.connection = databaseConnection.getConnection();
		String musicCategory = music.getCategory();
		String musicTitle = music.getTitle();
		String musicArtist = music.getArtist();
		String musicRecordLabel = music.getRecordLabel();
		int musicAvailability = music.getAvailability();
		int musicItemId = music.getItemID();

		try {
			query = "UPDATE music SET Category=?,Title=?,Artist=?,Record_Label=?,Availability=? WHERE Item_ID=? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, musicCategory);
			preparedStatement.setString(2, musicTitle);
			preparedStatement.setString(3, musicArtist);
			preparedStatement.setString(4, musicRecordLabel);
			preparedStatement.setInt(5, musicAvailability);
			preparedStatement.setInt(6, musicItemId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update music into database", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return false;
	}

	@Override
	public Boolean deleteMusic(Music music) {

		this.connection = databaseConnection.getConnection();
		try {
			query = "DELETE from music WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, music.getItemID());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete music from database", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return false;
	}

	private String prepareSearchQuery(MusicSearch requestDetails, String searchTerms) {

		if (0 == searchTerms.length()) {
			logger.log(Level.ALL, "No search terms are supplied");
			return null;
		}

		String query = "SELECT DISTINCT * FROM music WHERE ";
		String[] searchterms = searchTerms.split("\\s");
		for (String term : searchterms) {
			if (requestDetails.isSearchMusicAlbumName()) {
				query += "Title like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchMusicArtist()) {
				query += "Artist like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchMusicRecordLabel()) {
				query += "Record_Label like \"%" + term + "%\" or ";
			}
		}

		query = query.substring(0, query.length() - 4);
		return query;
	}

	@Override
	public List<LibraryItem> getMusicBySearchTerms(MusicSearch requestDetails, String searchTerms) {

		this.connection = databaseConnection.getConnection();
		List<LibraryItem> musics = new LinkedList<LibraryItem>();
		List<Music> tempMusics = new ArrayList<>();
		if (!requestDetails.isSearchInMusic()) {
			return musics;
		}
		String query = prepareSearchQuery(requestDetails, searchTerms);

		if (null == query) {
			return musics;
		}

		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			tempMusics = musicSetter.mapMusic(resultSet);
			musics.addAll(tempMusics);
			return musics;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Failed to prepare SQL statement OR execute a query OR parse a query resultSet", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return musics;
	}

	@Override
	public List<String> getMusicCategories() {
		this.connection = databaseConnection.getConnection();
		List<String> categories = new ArrayList<String>();
		query = "SELECT Distinct Category from music";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categories.add(resultSet.getString("Category"));
			}
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Music Categories", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return categories;
	}

	@Override
	public int getAvailability(int itemID) {

		int musicsAvailable = 0;
		try {
			this.connection = databaseConnection.getConnection();
			query = "Select Availability from music where Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				musicsAvailable = resultSet.getInt("Availability");
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the availability of Music", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return musicsAvailable;
	}

	public boolean checkMusicDuplicacy(Music music) {

		this.connection = databaseConnection.getConnection();
		String artistToBeAdded = music.getArtist();
		String titleToBeAdded = music.getTitle();
		boolean isMusicAvailable = false;

		query = "SELECT * FROM music where Title=? and Artist=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, titleToBeAdded);
			preparedStatement.setString(2, artistToBeAdded);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isMusicAvailable = true;
			} else {
				isMusicAvailable = false;
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of Musics", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return isMusicAvailable;
	}

	@Override
	public Boolean increaseCount(int itemID) {
		Boolean countIncrease = false;
		try {
			this.connection = databaseConnection.getConnection();
			query = "update music set count = count + 1 where Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			preparedStatement.execute();
			countIncrease = true;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error increasing count of Music", e);
		} finally {

			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		return countIncrease;
	}

	@Override
	public void updateAvailability(int itemId, int udatedAvailability) {
		
		try {
			this.connection = databaseConnection.getConnection();
			query = "update music set Availability =? where Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, udatedAvailability);
			preparedStatement.setInt(2, itemId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.ALL, "Check the SQL syntax", e);
		} catch (Exception e) {
			logger.log(Level.ALL, "Error updating availability of music", e);
		} finally {
			databaseConnection.closeConnection(resultSet, preparedStatement);
		}
		
	}

}
