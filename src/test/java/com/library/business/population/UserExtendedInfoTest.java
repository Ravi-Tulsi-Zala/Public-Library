package com.library.business.population;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.library.buisness.population.IUserBasicInfo;
import com.library.buisness.population.IUserExtendedInfo;
import com.library.buisness.population.UserBasicInfo;
import com.library.buisness.population.UserExtendedInfo;

public class UserExtendedInfoTest {
	
	private IUserExtendedInfo userExtendedInfo;

	@Before
	public void setUp() throws Exception {
		userExtendedInfo = new UserExtendedInfo("9028586967", "Alex Dunn");
	}

	@Test
	public void FullNameAndPhoneNumberTest() {
		assertEquals("9028586967", userExtendedInfo.getPhoneNumber());
		assertEquals("Alex Dunn", userExtendedInfo.getFullName());
	}
}
