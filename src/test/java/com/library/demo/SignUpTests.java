package com.library.demo;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.Logger;
import com.library.signUp.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpTests {
	static User user;

	@BeforeClass
	public static void pub() {

		user = new User();
	}

	@Test
	public void testPassword() {
		user.setPassword("devanshu");
		assertTrue(user.getPassword() != "");
		user.setPassword(null);
		assertTrue(user.getPassword() == "" || user.getPassword() == null);
	}

	@Test
	public void testEmailID() {
		Logger obj = Logger.loggerInstance();
		obj.writeLog("Hello man again!!");
	}

}
