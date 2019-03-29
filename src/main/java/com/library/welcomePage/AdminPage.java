package com.library.welcomePage;

public class AdminPage {
	private static boolean isAdminAvailable = false;

	public static boolean getAdminAvailable() {
		return isAdminAvailable;
	}
	
	public static void setAdminAvailable(boolean isAdminAvailable) {
		AdminPage.isAdminAvailable = isAdminAvailable;
	}
}
