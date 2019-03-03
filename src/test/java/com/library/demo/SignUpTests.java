package com.library.demo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.Logger;
import com.library.mockDB.SignUpMocked;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpTests {
	static SignUpMocked signUpMocked;
	static Map mapList;
	List arrayList;
	@BeforeClass
	public static void initializer() {
		signUpMocked = new SignUpMocked();
	}

	@Test
	public void testwithCorrectUserInfo() {
		mapList = signUpMocked.getMockData();
		
		UserBasicInfo userBasic = null;
		UserExtendedInfo userExtend = null;
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("clean-data")) {
				arrayList = (ArrayList) mapList.get("clean-data");
				userBasic = (UserBasicInfo) arrayList.get(0);
				assertEquals("123456789", userBasic.getPwd());
				assertEquals("devanshu1@gmail.com", userBasic.getEmail());
				userExtend = (UserExtendedInfo) arrayList.get(1);
				assertEquals("deva sriv", userExtend.getFullname());
				assertEquals("9024031714", userExtend.getPhone());
				assertEquals("123456789", userExtend.getCPassword());
				assertTrue(userBasic.getPwd() == userExtend.getCPassword());
				assertTrue(userExtend.getPhone().length() == 10);
				assertTrue(userBasic.getEmail().contains("@"));
			}
		}
	}

	@Test
	public void testwithINCorrectUserInfo() {
		mapList = signUpMocked.getCorruptMockData();
		UserBasicInfo userBasicInfo = null;
		UserExtendedInfo userExtendInfo=null;
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("corrupt-data")) {
				arrayList = (ArrayList) mapList.get("corrupt-data");
				userBasicInfo = (UserBasicInfo) arrayList.get(0);
				assertEquals("1qaz!QAZ", userBasicInfo.getPwd());
				assertEquals("devanshu0101@gmail.com", userBasicInfo.getEmail());
				userExtendInfo = (UserExtendedInfo) arrayList.get(1);
				assertEquals("devanshu sriv", userExtendInfo.getFullname());
				assertEquals("902", userExtendInfo.getPhone());
				assertEquals("1qazZAQ!", userExtendInfo.getCPassword());
				assertTrue(userBasicInfo.getPwd() != userExtendInfo.getCPassword());
				assertTrue(userExtendInfo.getPhone().length() != 10);
				assertTrue(userBasicInfo.getEmail().contains("@"));
			}
		}
	}

	@Test
	public void testLogger() {
		Logger logger = Logger.loggerInstance();
		logger.writeLog("Test message!");
	}
}
