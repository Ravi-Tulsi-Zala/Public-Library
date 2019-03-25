package com.library.signOut;

import javax.servlet.http.HttpSession;

public interface ISignOutObserver {
	public void notifyUserSignOut(HttpSession httpSession);
}
