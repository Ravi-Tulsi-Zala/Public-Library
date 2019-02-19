package com.library.buisness.population;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderMD5 implements IPasswordEncoder {

	private final String CHARACTER_ENCODING = "UTF-8"; // move it to
	private String salt;
	private MessageDigest messageDigestMD5;
	
	public PasswordEncoderMD5(String salt) {
		super();
		this.salt = salt;
		try {
			this.messageDigestMD5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			//Print to logger??? I rather re-throw it.
		}
	}
	
	@Override
	public String encode(String rawPassword) {
		// TODO Auto-generated method stub
		try {
			return messageDigestMD5.digest((rawPassword + salt).getBytes(CHARACTER_ENCODING)).toString();
		} catch (UnsupportedEncodingException e) {
			//Print to logger??? I rather re-throw it.
			return null;
		}
	}

}
