package com.library.signUp;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;


public class SignUpController {
	static Map.Entry<String,String> entryMap;    

	// Till DB is integrated values are validated against some dummy values.
	public static ArrayList<Map.Entry<String,String>> setUserData(User user,HttpServletRequest request) {
		
		ArrayList<Entry<String, String>> listofValidationErrors = new ArrayList<Map.Entry<String,String>>();
		listofValidationErrors.clear();
		if (user.getEmail()=="" || user.getEmail() == "devanshu.srivastava1@gmail.com") {
			 entryMap=  new AbstractMap.SimpleEntry<String, String>("email", "email cannot be the same.");
			 listofValidationErrors.add(entryMap);
		}
		else {
			 user.setEmail(request.getParameter("email"));
		}
		if(user.getPassword()=="" || !user.getPassword().matches("[A-Za-z0-9]*$")) {
			entryMap=  new AbstractMap.SimpleEntry<String, String>("password", "password should contain all these characters.[A-Za-z0-9]*$");
			listofValidationErrors.add(entryMap);
		}
		else {
			user.setPassword(request.getParameter("password"));
		}
		if(user.getCpassword()=="" || !user.getCpassword().matches("[A-Za-z0-9]*$") && !user.getCpassword().equals(user.getPassword())) {
			entryMap=  new AbstractMap.SimpleEntry<String, String>("cpassword", "password should contain all these characters.[A-Za-z0-9]*$");
			listofValidationErrors.add(entryMap);
		}
		else {
			user.setCpassword(request.getParameter("cpassword"));
		}
		if(user.getFullName()=="") {
			entryMap=  new AbstractMap.SimpleEntry<String, String>("fullName", "provide full name");
			listofValidationErrors.add(entryMap);
		}
		else {
			user.setFullName(request.getParameter("fullName"));
		}
		if(user.getPhoneNumber()=="" || user.getPhoneNumber().length()>0 && user.getPhoneNumber().length()<10) {
			entryMap=  new AbstractMap.SimpleEntry<String, String>("phoneNumber", "provide phone number");
			listofValidationErrors.add(entryMap);
		}
		else {
			user.setPhoneNumber(request.getParameter("phoneNumber"));
		}

		if(listofValidationErrors.size()==0) {
			connectDB(); //will be worked upon.
		}
		return listofValidationErrors;
	}

	private static void connectDB() {
		// TODO:DB calls

	}
}
