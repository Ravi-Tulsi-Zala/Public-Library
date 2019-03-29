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

import com.library.BussinessModelSetter.MusicSetter;
import com.library.IBussinessModelSetter.IMusicSetter;
import com.library.IDAO.IMusicDAO;
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
		List<Music> musics = new ArrayList<>();
		query = "SELECT * from music WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			musics = musicSetter.mapMusic(resultSet);
			music = musics.get(0);
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch music using id", e);
		}
		return music;
	}

	@Override
	public List<Music> getMusicByCategory(String category) {
		query = "SELECT * from music WHERE Category LIKE ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + category + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			musicsByCategory = musicSetter.mapMusic(resultSet);
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not fetch the list of music by the specific category", e);
		}
		return musicsByCategory;

	}

	@Override
	public int createMusic(Music music) {


		String musicCategory= music.getCategory();
		String musicTitle = music.getTitle();
		String musicArtist = music.getArtist();
		String musicRecordLabel = music.getRecordLabel();
		int musicAvailability = music.getAvailability();
		int recentlyAddedMusicId = 0;
		
		try {
			query = "INSERT INTO music (Category,Title,Artist,Record_Label,Availability) VALUES ( ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, musicCategory);
			preparedStatement.setString(2, musicTitle);
			preparedStatement.setString(3, musicArtist);
			preparedStatement.setString(4, musicRecordLabel);
			preparedStatement.setInt(5, musicAvailability);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			
			if (rs.next()) {
			    recentlyAddedMusicId = rs.getInt(1);
			}
			
			return recentlyAddedMusicId ;
			
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not create music entry in database", e);
		}
		return recentlyAddedMusicId;
	}

	@Override
	public Boolean updateMusic(Music music) {

		String musicCategory= music.getCategory();
		String musicTitle = music.getTitle();
		String musicArtist = music.getArtist();
		String musicRecordLabel = music.getRecordLabel();
		int musicAvailability = music.getAvailability();
		int musicItemId = music.getItemID();
		
		
		try {
			query = "UPDATE music SET Category=?,Title=?,Artist=?,Record_Label=?,Availability=? WHERE Item_ID=? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, musicCategory);
			preparedStatement.setString(2,musicTitle);
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
		List<Music> tempMusics = new ArrayList<>();
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
			tempMusics = musicSetter.mapMusic(resultSet);
			musics.addAll(tempMusics);
			return musics;
		} catch (SQLException e) {
			logger.log(Level.ALL, "Failed to prepare SQL statement OR execute a query OR parse a query resultSet", e);
		}

		return musics;
	}
}
