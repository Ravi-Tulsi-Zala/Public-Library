package com.library.controllers;

import java.util.List;
import java.util.Map.Entry;

public interface ISignInController {

	List<Entry<String, String>> authenticateSignIn();

	String checkUserCredential();

}
