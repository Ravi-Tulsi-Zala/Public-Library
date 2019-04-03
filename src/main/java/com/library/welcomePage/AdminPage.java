package com.library.welcomePage;

import com.library.messages.Messages;

public class AdminPage {
	private static boolean isAdminAvailable = false;
	private static String availableUserID;
	private static String clientActiveStatus = Messages.RegisterLogin.getMessage();

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

	public static String getClientActiveStatus() {
		return clientActiveStatus;
	}

	public static void setClientActiveStatus(String loggingStatus) {
		AdminPage.clientActiveStatus = loggingStatus;
	}
}
