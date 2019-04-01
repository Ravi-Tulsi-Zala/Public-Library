package com.library.validatations;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IUserDAO;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;

public class ValidateUserForms extends ValidateUserFormsAbstract {

	private static List<Entry<String, String>> listofValidationErrors = null;
	private static Map.Entry<String, String> entryMap = null;
	private static final String fullName = "fullName";
	private static final String password = "password";
	private static final String email = "email";
	private static final String cpassword = "cpassword";
	private static final String phoneNumber = "phoneNumber";
	private static ValidateUserForms instance = null;
	private static final Logger logger = LogManager.getLogger(ValidateUserForms.class);
	private IUserDAO userDAO;

	public static ValidateUserForms instance() {
		if (instance == null) {
			instance = new ValidateUserForms();
		}
		return instance;
	}

	public ValidateUserForms() {
		IDAOFactory factory = new DAOFactory();
		userDAO = factory.makeUserDAO();
	}

	private boolean validatePasswordLength(PasswordLengthValidation plenght, String passToCheck) {
		return plenght.isValidLength(passToCheck);
	}

	private boolean validatePasswordLower(PasswordLowercaseValidation plower, String passToCheck) {
		return plower.isValidLowerCase(passToCheck);
	}

	private boolean validatePasswordUpper(PasswordUppercaseValidation pupper, String passToCheck) {
		return pupper.isValidUpperCase(passToCheck);
	}

	private boolean validatePasswordSymbols(PasswordSymbolValidation psymbols, String passToCheck) {
		return psymbols.isValid(passToCheck);
	}

	private boolean validateEmailStrength(EmailStrength eStrength, String valueToCheck) {
		return eStrength.isValidEmail(valueToCheck);
	}

	private boolean validatePhoneStrength(PhoneStrength phoneStrength, String valueToCheck) {
		return phoneStrength.isValidPhoneNumber(valueToCheck);
	}

	private boolean emailPhoneValidations(String valueToCheck, boolean isPassword) throws Exception {
		boolean returnValue = false;
		for (int i = 0; i < emailPhoneListAttributes.size(); i++) {
			if (isPassword) {
				if (emailPhoneListAttributes.get(i) instanceof PhoneStrength) {
					PhoneStrength phoneStrength = (PhoneStrength) emailPhoneListAttributes.get(i);
					returnValue = validatePhoneStrength(phoneStrength, valueToCheck);
					if (!returnValue) {
						return returnValue;
					}
				}
			} else if (emailPhoneListAttributes.get(i) instanceof EmailStrength) {
				EmailStrength eStrength = (EmailStrength) emailPhoneListAttributes.get(i);
				returnValue = validateEmailStrength(eStrength, valueToCheck);
				if (!returnValue) {
					return returnValue;
				}
			}
		}
		return returnValue;
	}

	private boolean passwordValidations(String passToCheck) throws Exception {
		boolean returnValue = false;
		for (int i = 0; i < passwordListAttributes.size(); i++) {
			if (passwordListAttributes.get(i) instanceof PasswordLengthValidation) {
				PasswordLengthValidation plenght = (PasswordLengthValidation) passwordListAttributes.get(i);
				returnValue = validatePasswordLength(plenght, passToCheck);
				if (!returnValue) {
					return returnValue;
				}
			} else if (passwordListAttributes.get(i) instanceof PasswordLowercaseValidation) {
				PasswordLowercaseValidation plower = (PasswordLowercaseValidation) passwordListAttributes.get(i);
				returnValue = validatePasswordLower(plower, passToCheck);
				if (!returnValue) {
					return returnValue;
				}
			} else if (passwordListAttributes.get(i) instanceof PasswordUppercaseValidation) {
				PasswordUppercaseValidation pupper = (PasswordUppercaseValidation) passwordListAttributes.get(i);
				returnValue = validatePasswordUpper(pupper, passToCheck);
				if (!returnValue) {
					return returnValue;
				}
			} else if (passwordListAttributes.get(i) instanceof PasswordSymbolValidation) {
				PasswordSymbolValidation psymbols = (PasswordSymbolValidation) passwordListAttributes.get(i);
				returnValue = validatePasswordSymbols(psymbols, passToCheck);
				if (!returnValue) {
					return returnValue;
				}
			}
		}
		return returnValue;
	}

	public ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo) throws Exception {
		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		String userEmail = userBasicInfo.getEmail();
		String userPwd = userBasicInfo.getPassword();
		String userCPwd = userExtendedInfo.getCPassword();
		String userPhone = userExtendedInfo.getPhone();
		String userName = userExtendedInfo.getFullname();
		if (userEmail.isEmpty() || isWhitespace(userEmail) || !emailPhoneValidations(userEmail, false)) {
			mapperEntry(email, emailErrorStatement);
		} else if (userDAO.checkEmailIdExist(userEmail)) {
			emailErrorStatement = "Email already exists. Please register with different email";
			mapperEntry(email, emailErrorStatement);
		}
		if (userPwd.isEmpty() || isWhitespace(userPwd) || !passwordValidations(userPwd)) {
			mapperEntry(password, passwordErrorStatement);
		}
		if (userCPwd.isEmpty() || isWhitespace(userCPwd) || !passwordValidations(userCPwd)) {
			mapperEntry(cpassword, cpasswordErrorStatement);
		} else if (!userCPwd.equals(userPwd)) {
			mapperEntry(cpassword, cpasswordErrorStatement);
		}
		if (userName.isEmpty() || isWhitespace(userName)) {
			mapperEntry(fullName, blankErrorStatement);
		}
		if (userPhone.isEmpty() || isWhitespace(userPhone) || !emailPhoneValidations(userPhone, true)) {
			mapperEntry(phoneNumber, phoneErrorStatement);
		}
		logger.log(Level.ALL, "signUpUserData method implemented completely");
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	public ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo) throws Exception {
		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		String userEmail = userBasicInfo.getEmail();
		String userPwd = userBasicInfo.getPassword();

		if (userEmail.isEmpty() || isWhitespace(userEmail) || !emailPhoneValidations(userEmail, false)) {
			mapperEntry(email, emailErrorStatement);
		}
		if (userPwd.isEmpty() || isWhitespace(userPwd) || !passwordValidations(userPwd)) {
			mapperEntry(password, passwordErrorStatement);
		}
		logger.log(Level.ALL, "signInIserData method implemented completely.");
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	// http://www.java2s.com/Code/Java/Data-Type/ChecksiftheStringcontainsonlywhitespace.htm
	private boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	private void mapperEntry(String key, String val) {
		entryMap = new AbstractMap.SimpleEntry<String, String>(key, val);
		listofValidationErrors.add(entryMap);
	}
}