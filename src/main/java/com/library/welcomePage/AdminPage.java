package com.library.welcomePage;

public class AdminPage {
	private static boolean isAdminAvailable = false;
	private static String availableUserID;
	private static String loggingStatus = "Register / Login";

	public static boolean getAdminAvailable() {
		return isAdminAvailable;
	}

	public static void setAvailableAdmin(boolean isAdminAvailable) {
		AdminPage.isAdminAvailable = isAdminAvailable;
	}

	public static String getAvailableUserID() {
		return availableUserID;
	}

	public static void setAvailableUserID(String availableUserID) {
		AdminPage.availableUserID = availableUserID;
	}

	public static String getLoggingStatus() {
		return loggingStatus;
	}

	public static void setLoggingStatus(String loggingStatus) {
		AdminPage.loggingStatus = loggingStatus;
	}
	// TODO: call eugene's work for getting the emailID for the normail user and
	// change the name of this class to some other class.
	// generic class i would say.

}
