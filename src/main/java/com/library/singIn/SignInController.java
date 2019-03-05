package com.library.singIn;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	public boolean connectDB() {

		// TODO: call DB
		return true;
	}

	public void xmlReader() {
		String filePath = "AuthenticationRules.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("User");
			System.out.println("Root element :" + nList);
			// now XML is loaded as Document in memory, lets convert it to Object List
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					passwordRegex = eElement.getElementsByTagName("pwdRegex").item(0).getTextContent();
					emailRegex = eElement.getElementsByTagName("emailRegex").item(0).getTextContent();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Entry<String, String>> signInUserAfterAuthenticate(IUserBasicInfo userBasicInfo) {
		xmlReader();
		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		if (userBasicInfo.getEmail() == "" || !Pattern.compile(emailRegex).matcher(userBasicInfo.getEmail()).find()
				|| userBasicInfo.getEmail() == "devanshu.srivastava1@gmail.com") {
			entryMap = new AbstractMap.SimpleEntry<String, String>("email", "email cannot be the same.");
			listofValidationErrors.add(entryMap);
		}
		if (userBasicInfo.getPwd() == "" || !Pattern.compile(passwordRegex).matcher(userBasicInfo.getEmail()).find()) {
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
