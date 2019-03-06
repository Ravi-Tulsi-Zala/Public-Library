package com.library.singIn;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.library.common.Constants;
import com.library.common.XmlParser;
import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

public class AuthenticationFacade {

	static List<Entry<String, String>> listofValidationErrors;
	static Map.Entry<String, String> entryMap;
	private String passwordRegex;
	private String emailRegex;
	String filePath = "AuthenticationRules.xml";

	// Till DB is integrated values are validated against some dummy values.
	// In next sprint i will add the functionality to check validation with XML
	// file. Also will create a const file for string values.
	public ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo) {
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
		if (userExtendedInfo.getCPassword() == ""
				|| !Pattern.compile(passwordRegex).matcher(userExtendedInfo.getCPassword()).find()
						&& !userExtendedInfo.getCPassword().equals(userBasicInfo.getPwd())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("cpassword", Constants.PASSWORD_ERROR);
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getFullname() == "") {
			entryMap = new AbstractMap.SimpleEntry<String, String>("fullName", Constants.UNFILLED_ERROR);
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getPhone() == ""
				|| userExtendedInfo.getPhone().length() > 0 && userExtendedInfo.getPhone().length() < 10) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("phoneNumber", Constants.NUMBER_ERROR);
			listofValidationErrors.add(entryMap);
		}
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
//			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	public ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo) {
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
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
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
}
