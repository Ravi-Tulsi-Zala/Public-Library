package com.library.signIn;

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

	private static List<Entry<String, String>> listofValidationErrors = null;
	private static Map.Entry<String, String> entryMap = null;
	private static final String PASSWORD_REGEX = "passwordRegex";
	private static final String EMAIL_REGEX = "emailRegex";
	private static final String filePath = "AuthenticationRules.xml";
	private static final String fullName = "fullName";
	private static final String password = "password";
	private static final String email = "email";
	private static final String cpassword = "cpassword";
	private static final String phoneNumber = "phoneNumber";

	private String passwordRegex;
	private String emailRegex;

	// Till DB is integrated values are validated against some dummy values.
	// In next sprint i will add the functionality to check validation with XML
	// file. Also will create a const file for string values.
	public ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo) {
		try {
			setRegexRules();

			listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
			listofValidationErrors.clear();

			if (userBasicInfo.getEmail().equals("")
					|| !Pattern.compile(emailRegex).matcher(userBasicInfo.getEmail()).find()
					|| userBasicInfo.getEmail().equals("devanshu.srivastava1@gmail.com")) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(email, Constants.EMAIL_ERROR);
				listofValidationErrors.add(entryMap);
			}
			if (userBasicInfo.getPwd().equals("")
					|| !Pattern.compile(passwordRegex).matcher(userBasicInfo.getPwd()).find()) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(password, Constants.PASSWORD_ERROR);
				listofValidationErrors.add(entryMap);
			}
			if (userExtendedInfo.getCPassword().equals("")
					|| !Pattern.compile(passwordRegex).matcher(userExtendedInfo.getCPassword()).find()
							&& !userExtendedInfo.getCPassword().equals(userBasicInfo.getPwd())) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(cpassword, Constants.PASSWORD_ERROR);
				listofValidationErrors.add(entryMap);
			}
			if (userExtendedInfo.getFullname().equals("")) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(fullName, Constants.UNFILLED_ERROR);
				listofValidationErrors.add(entryMap);
			}
			if (userExtendedInfo.getPhone().equals("")
					|| userExtendedInfo.getPhone().length() > 0 && userExtendedInfo.getPhone().length() < 10) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(phoneNumber, Constants.NUMBER_ERROR);
				listofValidationErrors.add(entryMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	public ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo) {
		try {
			setRegexRules();
			listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
			listofValidationErrors.clear();

			// some string comparison will be excluded once i get the DB integrated.

			if (userBasicInfo.getEmail().equals("")
					|| !Pattern.compile(emailRegex).matcher(userBasicInfo.getEmail()).find()
					|| userBasicInfo.getEmail().equals("devanshu.srivastava1@gmail.com")) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(email, Constants.EMAIL_ERROR);
				listofValidationErrors.add(entryMap);
			}
			if (userBasicInfo.getPwd().equals("")
					|| !Pattern.compile(passwordRegex).matcher(userBasicInfo.getPwd()).find()) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(password, Constants.PASSWORD_ERROR);
				listofValidationErrors.add(entryMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	private void setRegexRules() {
		try {
			// this function will help in setting the regex rules into private data members.
			List<Map.Entry<String, String>> list = XmlParser.parse(filePath);
			for (int i = 0; i < list.size(); i++) {
				switch (list.get(i).getKey()) {
				case PASSWORD_REGEX:
					this.passwordRegex = list.get(i).getValue();
					break;
				case EMAIL_REGEX:
					this.emailRegex = list.get(i).getValue();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
