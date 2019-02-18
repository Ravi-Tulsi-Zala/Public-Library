package com.library.mockDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.library.signUp.User;
	
public class MockDatabase {
	Map mapObj;
	public String fullName;
	public String phoneNumber;
	public String email;
	public String password;
	public String cpassword;
	ArrayList arrItems;

	public MockDatabase() {
		
		mapObj = new HashMap<String,User>();
		//addMock();
	}

	public void addMock() {

		mapObj.put("Devanshu", "123456");

		mapObj.put("guru", "000");
	}

	public boolean verify(User user) {
			if(mapObj.get("dev")==user) {
				return true;
			}
		return false;

	}

	public Object getObject(String key) {
		return mapObj.get(key);

}
	public boolean register(User user) {
		arrItems = new ArrayList<String>();
		mapObj.put("dev",user);
		return true;
	}
	public User register1(User user) {
		arrItems = new ArrayList<String>();
		mapObj.put("dev",user);
		return (User) mapObj.get("dev");
	}
}
