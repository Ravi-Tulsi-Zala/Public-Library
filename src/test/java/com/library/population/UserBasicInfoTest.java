package com.library.population;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.library.population.IUserBasicInfo;
import com.library.population.UserBasicInfo;

public class UserBasicInfoTest {
	
	private IUserBasicInfo userBasicInfo;

	@Before
	public void setUp() throws Exception {
		userBasicInfo = new UserBasicInfo("abcd@gmail.com", "Anmidx^36HnkajIP9");
	}

	@Test
	public void EmailAndPasswordWriteRead() {
		assertEquals("abcd@gmail.com", userBasicInfo.getUserEmail());
		assertEquals("Anmidx^36HnkajIP9", userBasicInfo.getUserPassword());
	}
}
