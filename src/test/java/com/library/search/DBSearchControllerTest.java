package com.library.search;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.demo.LibraryApplication;

import javax.inject.Inject;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={LibraryApplication.class})
public class DBSearchControllerTest {
	
	@Inject
	private IDBSearchController dbSearchController;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

	}

}
