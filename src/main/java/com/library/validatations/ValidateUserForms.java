package com.library.validatations;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.library.DAO.BookDAO;
import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.businessModels.UserBasicInfo;

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

	public static ValidateUserForms instance() {
		if (instance == null) {
			instance = new ValidateUserForms();
		}
		return instance;
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

	private boolean emailPhoneValidations(String valueToCheck) throws Exception {
		boolean returnValue = false;
		for (int i = 0; i < emailPhoneListAttributes.size(); i++) {
			if (emailPhoneListAttributes.get(i) instanceof EmailStrength) {
				EmailStrength eStrength = (EmailStrength) emailPhoneListAttributes.get(i);
				returnValue = validateEmailStrength(eStrength, valueToCheck);
				if (!returnValue) {
					return returnValue;
				}
			} else if (emailPhoneListAttributes.get(i) instanceof PhoneStrength) {
				PhoneStrength phoneStrength = (PhoneStrength) emailPhoneListAttributes.get(i);
				returnValue = validatePhoneStrength(phoneStrength, valueToCheck);
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

		if (userBasicInfo.getEmail().isBlank() || !emailPhoneValidations(userBasicInfo.getEmail())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(email, emailErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		if (userBasicInfo.getPassword().isBlank() || !passwordValidations(userBasicInfo.getPassword())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(password, passwordErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getCPassword().isBlank() || !passwordValidations(userBasicInfo.getPassword())) {

			entryMap = new AbstractMap.SimpleEntry<String, String>(cpassword, passwordErrorStatement);
			listofValidationErrors.add(entryMap);
		} else if (!userExtendedInfo.getCPassword().equals(userBasicInfo.getPassword())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(cpassword, cpasswordErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getFullname().isBlank()) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(fullName, blankErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		if (userExtendedInfo.getPhone().isBlank() || !emailPhoneValidations(userExtendedInfo.getPhone())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(phoneNumber, phoneErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		logger.log(Level.ALL, "signUpUserData method implemented completely");
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	public ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo) throws Exception {
		listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
		listofValidationErrors.clear();
		if (userBasicInfo.getEmail().isBlank() || !emailPhoneValidations(userBasicInfo.getEmail())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(email, emailErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		if (userBasicInfo.getPassword().isBlank() || !passwordValidations(userBasicInfo.getPassword())) {
			entryMap = new AbstractMap.SimpleEntry<String, String>(password, passwordErrorStatement);
			listofValidationErrors.add(entryMap);
		}
		logger.log(Level.ALL, "signInIserData method implemented completely.");
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

}
