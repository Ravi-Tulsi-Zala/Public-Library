package com.library.mockDB;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;
	
public class SignUpMocked {
	public String fullName;
	public String phoneNumber;
	public String email;
	public String password;
	public String cpassword;
	ArrayList arrItems;
	IUserBasicInfo basic;
	IUserExtendedInfo extended;
Map mapObj;
	public SignUpMocked() {
		
//		mapObj = new HashMap<String,ArrayList>();
		basic = new UserBasicInfo();
		extended = new UserExtendedInfo();
		
	}

	public Map addCorruptData() {
		ArrayList arrItems = new ArrayList<String>();
		Map mapObj = new HashMap<String,ArrayList>();
		basic.setEmail("devanshu0101@gmail.com");
		basic.setPwd("1qaz!QAZ");
		extended.setCPassword("1qazZAQ!");
		extended.setFullname("devanshu sriv");
		extended.setPhone("902");
		arrItems.add(basic);
		arrItems.add(extended);
		mapObj.put("corrupt-data",arrItems);
		return mapObj;
	}
	
	public Map addMock() {
		arrItems = new ArrayList<String>();
		mapObj = new HashMap<String,ArrayList>();
		basic.setEmail("devanshu1@gmail.com");
		basic.setPwd("123456789");
		extended.setCPassword("123456789");
		extended.setFullname("deva sriv");
		extended.setPhone("9024031714");
		arrItems.add(basic);
		arrItems.add(extended);
		mapObj.put("clean-data",arrItems);
		return mapObj;
	}

	public boolean verify(User user) {
//			if(mapObj.get("dev")==user) {
//				return true;
//			}
		return false;

	}

	
	public boolean register(User user) {
		arrItems = new ArrayList<String>();
		//mapObj.put("dev",user);
		return true;
	}
	public User register1(User user) {
		arrItems = new ArrayList<String>();
	//	mapObj.put("dev",user);
		return null;//(User) mapObj.get("dev");
	}
}
