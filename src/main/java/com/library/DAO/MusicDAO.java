package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.BussinessModelSetter.MusicSetter;
import com.library.IBussinessModelSetter.IMusicSetter;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Music;
import com.library.dbConnection.DatabaseConnection;
import com.library.search.BookSearch;
import com.library.search.IMusicSearchRequestDetails;
import com.library.search.MusicSearch;

public class MusicDAO implements IMusicDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	IMusicSetter musicMapper = new MusicSetter();
	private static final Logger logger = LogManager.getLogger(MusicDAO.class);

	public MusicDAO() {

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();

		} catch (Exception e) {
			logger.log(Level.ALL, "Unable to connect to database", e);
		}
	}

	@Override
	public Music getMusicById(int itemID) {

		Music music = new Music();

		query = "SELECT * from music WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = musicMapper.mapMusic(resultSet);
			}
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch music using id", e);
		}
		return music;
	}

	@Override
	public List<Music> getMusicByCategory(String category) {

		Music music = new Music();
		query = "SELECT * from music WHERE Category LIKE ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + category + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music = musicMapper.mapMusic(resultSet);
				musicsByCategory.add(music);
			}
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch the list of music by the specific category", e);
		}
		return musicsByCategory;

	}

	@Override
	public Boolean createMusic(Music music) {

		try {
			query = "INSERT INTO music (Category,Title,Artist,Record_Label,Availability) VALUES ( ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, music.getCategory());
			preparedStatement.setString(2, music.getTitle());
			preparedStatement.setString(3, music.getArtist());
			preparedStatement.setString(4, music.getRecordLabel());
			preparedStatement.setInt(5, music.getAvailability());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not create music entry in database", e);
		}
		return false;
	}

	@Override
	public Boolean updateMusic(Music music) {

		try {
			query = "UPDATE music SET Category=?,Title=?,Artist=?,Record_Label=?,Availability=? WHERE Item_ID=? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, music.getCategory());
			preparedStatement.setString(2, music.getTitle());
			preparedStatement.setString(3, music.getArtist());
			preparedStatement.setString(4, music.getRecordLabel());
			preparedStatement.setInt(5, music.getAvailability());
			preparedStatement.setInt(6, music.getItemID());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update movie into database", e);
		}
		return false;
	}

	@Override
	public Boolean deleteMusic(Music music) {
		try {
			query = "DELETE from music WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, music.getItemID());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete movie from database", e);
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
		List<LibraryItem> musics = new LinkedList<LibraryItem>();
		if(!requestDetails.isSearchInMusic()) {
			return musics;
		}
		
		Music music;
		String query = prepareSearchQuery(requestDetails, searchTerms);
		
		if(null ==query) {
			return musics;
		}

		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = musicMapper.mapMusic(resultSet);
				musics.add(music);
			}

			return musics;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Failed to prepare SQL statement OR execute a query OR parse a query resultSet", e);
		}

		return musics;
	}
}
