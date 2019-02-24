package com.library.demo;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.Logger;
import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpTests {
	static IUserBasicInfo basic;
	static IUserExtendedInfo extended;

	@BeforeClass
	public static void pub() {
		basic = new UserBasicInfo();
		extended = new UserExtendedInfo();
	}

	
//	public void test
	
	@Test
	public void testPassword() {
		basic.setPwd("devanshu");
		assertTrue(basic.getPwd()!= "");
		basic.setPwd(null);
		assertTrue(basic.getPwd()== "" || basic.getPwd()== null);
	}

	@Test
	public void testLogger() {
		Logger obj = Logger.loggerInstance();
		obj.writeLog("Hello man again!!");
	}
	
	@Test
	public void testBasicInfo() {
		SignUpController abc = new SignUpController();
		
		assertTrue(!abc.minimumInfo());
	}
//	
//	@Test
//	public void testEmailID() {
//		Logger obj = Logger.loggerInstance();
//		obj.writeLog("Hello man again!!");
//	}
//	
//	@Test
//	public void testEmailID() {
//		Logger obj = Logger.loggerInstance();
//		obj.writeLog("Hello man again!!");
//	}
//	
//	@Test
//	public void testEmailID() {
//		Logger obj = Logger.loggerInstance();
//		obj.writeLog("Hello man again!!");
//	}

}
