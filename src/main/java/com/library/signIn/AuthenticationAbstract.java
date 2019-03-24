package com.library.signIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.xmlParser.XmlParser;

//Template pattern implemented in this class and in its child class. I have implemented setValidationRules() and setErrorStringRules() that are being used by the child class many times.
// Abstract functions are also added in this class and that are used by child class, where it changes the flow of action as required.
// Hence, template patter.
public abstract class AuthenticationAbstract {
	// Roots in the authentication xml file are initialized below.
	private static final String passwordRegexKeyRoot = "passwordRegex";
	private static final String emailRegexKeyRoot = "emailRegex";
	private static final String phoneCheckKeyRoot = "phoneCheck";
	private static final String adminIDCheckKeyRoot = "adminId";
	private static final String adminPasswordCheckKeyRoot = "adminPwd";
	private static final String emailErrorKeyRoot = "emailErrorString";
	private static final String passwordErrorKeyRoot = "passwordErrorString";
	private static final String emptyErrorKeyRoot = "emptyErrorString";
	private static final String phoneErrorKeyRoot = "phoneErrorString";
	private static final String cpasswordErrorKeyRoot = "cpasswordErrorString";
	private static final String saltKeyRoot = "salt";

	// Validations that will be checked via externalized file.
	protected String passwordRegex;
	protected String emailRegex;
	protected int phoneCheck;
	protected String passwordErrorStatement;
	protected String emailErrorStatement;
	protected String blankErrorStatement;
	protected String phoneErrorStatement;
	protected String cpasswordErrorStatement;
	protected static String isAdminPwd;
	protected static String isAdmin;
	public static String saltValue;

	private static final String filePathToValidations = "AuthenticationRules.xml";
	private static final String filePathToErrorStatements = "ValidationStatements.xml";

	public abstract ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo);

	public abstract ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo);

	protected void setValidationRules() {
		try {
			List<Map.Entry<String, String>> list = XmlParser.parse(filePathToValidations);
			for (int i = 0; i < list.size(); i++) {
				String getKeyFromList = list.get(i).getKey();
				String getValueFromList = list.get(i).getValue();
				switch (getKeyFromList) {
				case passwordRegexKeyRoot:
					this.passwordRegex = getValueFromList;
					break;
				case emailRegexKeyRoot:
					this.emailRegex = getValueFromList;
					break;
				case phoneCheckKeyRoot:
					this.phoneCheck = Integer.parseInt(getValueFromList);
					break;
				case adminIDCheckKeyRoot:
					isAdmin = getValueFromList;
					break;
				case adminPasswordCheckKeyRoot:
					isAdminPwd = getValueFromList;
					break;
				case saltKeyRoot:
					saltValue = getValueFromList;
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setErrorStringRules() {
		try {
			List<Map.Entry<String, String>> list = XmlParser.parse(filePathToErrorStatements);
			for (int i = 0; i < list.size(); i++) {
				switch (list.get(i).getKey()) {
				case emailErrorKeyRoot:
					this.emailErrorStatement = list.get(i).getValue();
					break;
				case emptyErrorKeyRoot:
					this.blankErrorStatement = list.get(i).getValue();
					break;
				case passwordErrorKeyRoot:
					this.passwordErrorStatement = list.get(i).getValue();
					break;
				case phoneErrorKeyRoot:
					this.phoneErrorStatement = list.get(i).getValue();
					break;
				case cpasswordErrorKeyRoot:
					this.cpasswordErrorStatement = list.get(i).getValue();
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