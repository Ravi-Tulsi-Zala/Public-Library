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
import com.library.businessModels.Music;
import com.library.dbConnection.DatabaseConnection;
import com.library.search.IMusicSearchRequestDetails;

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

		query = "SELECT * from music WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = musicSetter.mapMusic(resultSet);
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
				music = musicSetter.mapMusic(resultSet);
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

	private void prepareSearchQuery(IMusicSearchRequestDetails requestDetails) {

		if (0 == requestDetails.getSearchTerms().length()) {
			// logger.log("ERROR: Search terms length is zero.");
		}

		query = "SELECT DISTINCT * FROM music WHERE ";
		String[] searchterms = requestDetails.getSearchTerms().split("\\s");
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
	}

	@Override
	public LinkedList<Music> getMusicBySearchTerms(IMusicSearchRequestDetails searchRequestDetails) {
		LinkedList<Music> musics = new LinkedList<Music>();
		Music music;
		prepareSearchQuery(searchRequestDetails);

		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				music = musicSetter.mapMusic(resultSet);
				musics.add(music);
			}

			return musics;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return musics;
	}
}
