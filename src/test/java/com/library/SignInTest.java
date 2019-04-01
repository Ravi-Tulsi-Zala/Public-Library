package com.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.UserBasicInfo;
import com.library.mockDB.SignInMocked;
import com.library.mockDB.SignUpMocked;

public class SignInTest {
	private static SignInMocked signInMocked;
	private static Map mapList;
	private UserBasicInfo userBasicInfo = null;
	private List arrayList;

	@BeforeClass
	public static void initializer() {
		signInMocked = new SignInMocked();
	}

	@Test
	public void testSignInwithCorrectUserInfo() {
		mapList = signInMocked.getMockData();

		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("clean-data")) {
				arrayList = (ArrayList) mapList.get("clean-data");
				userBasicInfo = (UserBasicInfo) arrayList.get(0);
				assertEquals("123456789", userBasicInfo.getPassword());
				assertEquals("devanshu1@gmail.com", userBasicInfo.getEmail());
				assertTrue(userBasicInfo.getEmail().contains("@"));
			}
		}
	}

	@Test
	public void testSignInwithINCorrectUserInfo() {
		mapList = signInMocked.getCorruptMockData();
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("corrupt-data")) {
				arrayList = (ArrayList) mapList.get("corrupt-data");
				userBasicInfo = (UserBasicInfo) arrayList.get(0);
				assertEquals("1qaz!QAZ", userBasicInfo.getPassword());
				assertEquals("devanshu0101@gmail.com", userBasicInfo.getEmail());
			}
		}
	}

	@Test
	public void testSalt() {
		IUserBasicInfo userBasicInfo = signInMocked.addSaltToMockData();
		assertEquals(userBasicInfo.getEmail(),"devanshu1@gmail.com");
		assertNotEquals(userBasicInfo.getPassword(),"123456789");
		assertEquals(userBasicInfo.getPassword(),"123456789-LMS");
	}
	
}
