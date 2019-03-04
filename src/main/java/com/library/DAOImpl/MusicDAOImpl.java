package com.library.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.library.BusinessModels.Music;
import com.library.DAO.IMusicDAO;
import com.library.dbConnection.DatabaseConnection;

public class MusicDAOImpl implements IMusicDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;

	public MusicDAOImpl() {

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
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
				music.setTitle(resultSet.getString("Title"));
				music.setCategory(resultSet.getString("Category"));
				music.setRecordLabel(resultSet.getString("Record_Label"));
				music.setArtist(resultSet.getString("Artist"));
				music.setAvailability(resultSet.getInt("Availability"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return music;
	}

	@Override
	public Music getMusicByTitle(String musicTitle) {

		Music music = new Music();
		query = "SELECT * from music WHERE Title = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, musicTitle);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music.setItemID(resultSet.getInt("Item_ID"));
				music.setCategory(resultSet.getString("Category"));
				music.setRecordLabel(resultSet.getString("Record_Label"));
				music.setArtist(resultSet.getString("Artist"));
				music.setAvailability(resultSet.getInt("Availability"));
				music.setTitle(resultSet.getString("Title"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return music;
	}

	@Override
	public List<Music> getMusicByArtistName(String musicArtistName) {

		Music music = new Music();
		query = "SELECT * from music WHERE Artist = ?";
		List<Music> musicsByArtistName = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, musicArtistName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music.setItemID(resultSet.getInt("Item_ID"));
				music.setCategory(resultSet.getString("Category"));
				music.setRecordLabel(resultSet.getString("Record_Label"));
				music.setArtist(resultSet.getString("Artist"));
				music.setAvailability(resultSet.getInt("Availability"));
				music.setTitle(resultSet.getString("Title"));
				musicsByArtistName.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicsByArtistName;

	}

	@Override
	public List<Music> getMusicByCategory(String category) {

		Music music = new Music();
		query = "SELECT * from music WHERE Category = ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, category);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music.setItemID(resultSet.getInt("Item_ID"));
				music.setCategory(resultSet.getString("Category"));
				music.setRecordLabel(resultSet.getString("Record_Label"));
				music.setArtist(resultSet.getString("Artist"));
				music.setAvailability(resultSet.getInt("Availability"));
				music.setTitle(resultSet.getString("Title"));
				musicsByCategory.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicsByCategory;

	}

	@Override
	public List<Music> getMusicByRecordLabel(String recordLabel) {

		Music music = new Music();
		query = "SELECT * from music WHERE Record_Label = ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, recordLabel);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music.setItemID(resultSet.getInt("Item_ID"));
				music.setCategory(resultSet.getString("Category"));
				music.setRecordLabel(resultSet.getString("Record_Label"));
				music.setArtist(resultSet.getString("Artist"));
				music.setAvailability(resultSet.getInt("Availability"));
				music.setTitle(resultSet.getString("Title"));
				musicsByCategory.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicsByCategory;
	}

	@Override
	public Boolean createMusic(Music music) {

		try {
			query = "INSERT INTO music (Item_ID,Category,Title,Artist,Record_Label,Availability) VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, music.getItemID());
			preparedStatement.setString(2, music.getCategory());
			preparedStatement.setString(3, music.getTitle());
			preparedStatement.setString(4, music.getArtist());
			preparedStatement.setString(5, music.getRecordLabel());
			preparedStatement.setInt(6, music.getAvailability());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
