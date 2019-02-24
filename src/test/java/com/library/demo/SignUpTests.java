package com.library.demo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.Logger;
import com.library.mockDB.SignUpMocked;
import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpTests {
	static SignUpMocked _signUpMocked;
	static Map mapList, mapList1;

	@BeforeClass
	public static void pub() {
		_signUpMocked = new SignUpMocked();
		
//		mapList1 = _signUpMocked.addCorruptData();
	}

//	public void test

	@Test
	public void testwithCorrectUserInfo() {
		mapList = _signUpMocked.addMock();
		ArrayList arrayVal;
		UserBasicInfo userBasic = null;
		UserExtendedInfo userExtend;
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("clean-data")) {
				arrayVal = (ArrayList) mapList.get("clean-data");
				userBasic = (UserBasicInfo) arrayVal.get(0);
				assertEquals("123456789", userBasic.getPwd());
				assertEquals("devanshu1@gmail.com", userBasic.getEmail());
				userExtend = (UserExtendedInfo) arrayVal.get(1);
				assertEquals("deva sriv", userExtend.getFullname());
				assertEquals("9024031714", userExtend.getPhone());
				assertEquals("123456789", userExtend.getCPassword());
				
				assertTrue(userBasic.getPwd()==userExtend.getCPassword());
				assertTrue(userExtend.getPhone().length()==10);
			}
		}
	}	
	@Test
	public void testwithINCorrectUserInfo() {
		mapList = _signUpMocked.addCorruptData();
		ArrayList arrayVal;
		UserBasicInfo userBasic = null;
		UserExtendedInfo userExtend;
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("corrupt-data")) {
				arrayVal = (ArrayList) mapList.get("corrupt-data");
				userBasic = (UserBasicInfo) arrayVal.get(0);
				assertEquals("1qaz!QAZ", userBasic.getPwd());
				assertEquals("devanshu0101@gmail.com", userBasic.getEmail());
				userExtend = (UserExtendedInfo) arrayVal.get(1);
				assertEquals("devanshu sriv", userExtend.getFullname());
				assertEquals("902", userExtend.getPhone());
				assertEquals("1qazZAQ!", userExtend.getCPassword());
				
				assertTrue(userBasic.getPwd()==userExtend.getCPassword());
				assertTrue(userExtend.getPhone().length()==10);
			}
		}
	}

	@Test
	public void testLogger() {
		Logger obj = Logger.loggerInstance();
		obj.writeLog("Hello man again!!");
	}

//	@Test
//	public void testBasicInfo() {
//		SignUpController abc = new SignUpController();
//
//		assertTrue(!abc.minimumInfo());
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
//	
//	@Test
//	public void testEmailID() {
//		Logger obj = Logger.loggerInstance();
//		obj.writeLog("Hello man again!!");
//	}

}
