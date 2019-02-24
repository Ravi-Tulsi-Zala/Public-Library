package com.library.signUp;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.library.mockDB.MockDatabase;

public class SignUpController {
	IUserBasicInfo basic;
	IUserExtendedInfo extended;
	User user;
	MockDatabase mockDataBase;

	public  SignUpController() {
		
	}
	
	public SignUpController(IUserBasicInfo basic, IUserExtendedInfo extended) {
		this.basic = basic;
		this.extended = extended;
//		clientValidation();
	}

	
	public void connctDB() {
		// TODO:DB calls
		
	}

	public boolean minimumInfo() {
		if(basic.getEmail()!=null && basic.getEmail()!=""
				&& basic.getPwd()!=null && basic.getPwd()!="") {
			return true;
		}
		return false;
	}
	
	public String clientValidation() {

		int value = 1;
		if (value == 1) {

		}
		return "dev";
//		 JSONParser parser = new JSONParser();
//		 try {
//			 
//	            Object obj = parser.parse(new FileReader("firstBarGraph.json"));
//	 
//	            JSONObject jsonObject = (JSONObject) obj;
//	 
//	            String name = (String) jsonObject.get("Name");
//	            String author = (String) jsonObject.get("Author");
//	            JSONArray companyList = (JSONArray) jsonObject.get("Company List");
//	 
//	            System.out.println("Name: " + name);
//	            System.out.println("Author: " + author);
//	            System.out.println("\nCompany List:");
//	            Iterator<String> iterator = companyList.iterator();
//	            while (iterator.hasNext()) {
//	                System.out.println(iterator.next());
//	            }
//	 
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
	}

//	public boolean registerUser() {
//		return mockDataBase.register(user);
//	}
//
//	public boolean verifyCredentials() {
//		return mockDataBase.verify(user);
//	}
//	public User call() {
//		return (User)mockDataBase.getObject("dev");
//	}

}
