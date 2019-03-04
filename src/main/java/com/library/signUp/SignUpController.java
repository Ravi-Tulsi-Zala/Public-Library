package com.library.signUp;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.library.interfaces.IUserExtendedInfo;
import com.library.interfaces.IUserBasicInfo;

public class SignUpController {
	static Map.Entry<String, String> entryMap;
	static List<Entry<String, String>> listofValidationErrors;

	// Till DB is integrated values are validated against some dummy values. 
	// In next sprint i will add the functionality to check validation with XML file. Also will create a const file for string values.
	public ArrayList<Map.Entry<String, String>> insertInDBIfAuthenticate(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo) {

		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		if (userBasicInfo.getEmail() == "" || !userBasicInfo.getEmail().contains("@")  ||userBasicInfo.getEmail() == "devanshu.srivastava1@gmail.com") {
			entryMap = new AbstractMap.SimpleEntry<String, String>("email", "email cannot be the same.");
			listofValidationErrors.add(entryMap);
		}
		if (userBasicInfo.getPwd() == "" || !userBasicInfo.getPwd().matches("[A-Za-z0-9]*$")) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("password",
					"password should contain all these characters.[A-Za-z0-9]*$");
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getCPassword() == "" || !userExtendedInfo.getCPassword().matches("[A-Za-z0-9]*$")
				&& !userExtendedInfo.getCPassword().equals(userBasicInfo.getPwd())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("cpassword",
					"password should contain all these characters.[A-Za-z0-9]*$");
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getFullname() == "") {
			entryMap = new AbstractMap.SimpleEntry<String, String>("fullName", "provide full name");
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getPhone() == "" || userExtendedInfo.getPhone().length() > 0 && userExtendedInfo.getPhone().length() < 10) {
			entryMap = new AbstractMap.SimpleEntry<String, String>("phoneNumber", "provide phone number");
			listofValidationErrors.add(entryMap);
		}
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	private static void connectDB() {
		// TODO:DB calls

	}
}
