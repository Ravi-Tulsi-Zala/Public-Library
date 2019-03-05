package com.library.singIn;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.library.common.Constants;
import com.library.common.XmlParser;
import com.library.interfaces.IUserBasicInfo;
import com.library.mockDB.SignUpMocked;

public class SignInController {

	IUserBasicInfo basic;
	User user;
	SignUpMocked mockDataBase;
	static List<Entry<String, String>> listofValidationErrors;
	static Map.Entry<String, String> entryMap;
	private String passwordRegex;
	private String emailRegex;
	String filePath = "AuthenticationRules.xml";

	public boolean connectDB() {

		// TODO: call DB
		return true;
	}

	public void xmlReader() {
		// this function will help in setting the regex rules into private data members.
		List<Map.Entry<String, String>> list = XmlParser.parser(filePath);
		for (int i = 0; i < list.size(); i++) {
			switch (list.get(i).getKey()) {
			case "passwordRegex":
				passwordRegex = list.get(i).getValue();
				break;
			case "emailRegex":
				emailRegex = list.get(i).getValue();
				break;
			default:
				break;
			}
		}
	}

	public ArrayList<Entry<String, String>> signInUserAfterAuthenticate(IUserBasicInfo userBasicInfo) {
		xmlReader();
		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		if (userBasicInfo.getEmail() == "" || !Pattern.compile(emailRegex).matcher(userBasicInfo.getEmail()).find()
				|| userBasicInfo.getEmail() == "devanshu.srivastava1@gmail.com") {
			entryMap = new AbstractMap.SimpleEntry<String, String>("email", Constants.EMAIL_ERROR);
			listofValidationErrors.add(entryMap);
		}
		if (userBasicInfo.getPwd() == "" || !Pattern.compile(passwordRegex).matcher(userBasicInfo.getPwd()).find()) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("password", Constants.PASSWORD_ERROR);
			listofValidationErrors.add(entryMap);
		}
		if (listofValidationErrors.size() == 0) {
			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

}
