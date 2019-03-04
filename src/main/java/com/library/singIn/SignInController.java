package com.library.singIn;

import java.io.Console;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.mockDB.SignUpMocked;

public class SignInController {

	IUserBasicInfo basic;
	User user;
	SignUpMocked mockDataBase;
	static List<Entry<String, String>> listofValidationErrors;
	static Map.Entry<String, String> entryMap;

	public boolean connectDB() {
		//TODO: call DB
		return true;
	}

	public ArrayList<Entry<String, String>> signInUserAfterAuthenticate(IUserBasicInfo userBasicInfo) {
		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		if (userBasicInfo.getEmail() == "" || !userBasicInfo.getEmail().contains("@")  ||userBasicInfo.getEmail() == "devanshu.srivastava1@gmail.com") {
			entryMap = new AbstractMap.SimpleEntry<String, String>("email", "email cannot be the same.");
			listofValidationErrors.add(entryMap);
		}
		if (userBasicInfo.getPwd() == "" ||
				!userBasicInfo.getPwd().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("password",
					"password should contain all these characters.[A-Za-z0-9]*$");
			listofValidationErrors.add(entryMap);
		}
		if (listofValidationErrors.size() == 0) {
			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

}
