package com.library.model;

import com.library.itemSearch.SearchQuery;
import com.library.itemSearch.SearchResult;
import com.library.population.IMyUser;
import com.library.population.IUserBasicInfo;
import com.library.population.IUserInfo;


public class DataBase implements IDataBase {
	
	private static DataBase instance = null;
	
	private DataBase() {}
	
	public static DataBase instance() {
		if(instance==null) {
			instance = new DataBase();
		}
		return instance;
	}

	@Override
	public boolean registerNewUser(IUserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IMyUser loadUser(IUserBasicInfo basicInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult search(SearchQuery searchRequestDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
