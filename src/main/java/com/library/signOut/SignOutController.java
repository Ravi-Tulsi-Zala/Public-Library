package com.library.signOut;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.library.signIn.AuthenticatedUsers;

public class SignOutController {
	private static SignOutController instance = null;
	private AuthenticatedUsers authenticatedUsers = null;
	private LinkedList<ISignOutObserver> signOutObservers = null;
	private static final Logger logger = LogManager.getLogger(SignOutController.class);
	
	private SignOutController() {
		authenticatedUsers = AuthenticatedUsers.instance();
		signOutObservers = new LinkedList<ISignOutObserver>();
	}	

	synchronized public static SignOutController instance() {
		if(null == instance) {
			instance = new SignOutController();
		}
		
		return instance;
	}
	
	public void signOutUser(HttpSession httpSession) {
		if(authenticatedUsers.userIsAuthenticated(httpSession)) {
			authenticatedUsers.removeAuthenticatedUser(httpSession);
			notifyUserSignOut(httpSession);			
		} else {
			logger.log(Level.ALL, "Attempt to sign out a not signed in user with session id %s", httpSession.getId());
		}
	}

	private void notifyUserSignOut(HttpSession httpSession) {
		for(ISignOutObserver observer : signOutObservers) {
			observer.notifyUserSignOut(httpSession);
		}
	}
	
	public void registerAsSignOutObserver(ISignOutObserver observer) {
		signOutObservers.add(observer);
	}
}
