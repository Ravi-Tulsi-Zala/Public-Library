package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.library.DAOMapper.IMusicMapper;
import com.library.DAOMapperImpl.MusicMapper;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Music;
import com.library.dbConnection.DatabaseConnection;
import com.library.search.IMusicSearchRequestDetails;

public class MusicDAO implements IMusicDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	IMusicMapper musicMapper = new MusicMapper();

	public MusicDAO() {

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
			while(resultSet.next())
			{
				music = musicMapper.mapMusic(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return music;
	}

	@Override
	public List<Music> getMusicByTitle(String musicTitle) {

		Music music = new Music();
		List<Music> lisOfMusicByTitle = new ArrayList<Music>();

		query = "SELECT * from music WHERE Title LIKE ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+musicTitle+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				music = new Music();
				music = musicMapper.mapMusic(resultSet);
				lisOfMusicByTitle.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lisOfMusicByTitle;
	}

	@Override
	public List<Music> getMusicByArtistName(String musicArtistName) {

		Music music = new Music();
		query = "SELECT * from music WHERE Artist LIKE ?";
		List<Music> musicsByArtistName = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+musicArtistName+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music = musicMapper.mapMusic(resultSet);
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
		query = "SELECT * from music WHERE Category LIKE ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+category+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music = musicMapper.mapMusic(resultSet);
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
		query = "SELECT * from music WHERE Record_Label LIKE ?";
		List<Music> musicsByCategory = new ArrayList<Music>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+recordLabel+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				music = new Music();
				music = musicMapper.mapMusic(resultSet);
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
			query = "INSERT INTO music (Category,Title,Artist,Record_Label,Availability) VALUES ( ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, music.getCategory());
			preparedStatement.setString(2, music.getTitle());
			preparedStatement.setString(3, music.getArtist());
			preparedStatement.setString(4, music.getRecordLabel());
			preparedStatement.setInt(5, music.getAvailability());
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
	
	private void prepareSearchQuery(IMusicSearchRequestDetails requestDetails) {
		query = "SELECT * FROM music WHERE ";
		String[] searchterms = requestDetails.getSearchTerms().split("\\s");
		for(String term : searchterms) {
			if(requestDetails.isSearchMusicAlbumName()) {
				query += "Title like \"%" + term + "%\" or ";
			}
			if(requestDetails.isSearchMusicArtist()) {
				query += "Artist like \"%" + term + "%\" or ";
			}
			if(requestDetails.isSearchMusicRecordLabel()) {
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
			preparedStatement  = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(!resultSet.next())
			{
				return null;
			}
			do
			{
				music = musicMapper.mapMusic(resultSet);
				musics.add(music);
			} while(resultSet.next());
			
			return musics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
