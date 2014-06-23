package com.listnow.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.listnow.hosting.dao.Group;
import com.listnow.hosting.dao.User;
import com.listnow.hosting.guice.MainModule;
import com.listnow.hosting.service.ListnowPersistenceInitializer;
import com.listnow.hosting.service.ListnowService;

public class UserTest {
	static Injector injector;
	static ListnowService service;

	@BeforeClass
	public static void init() {
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(ListnowPersistenceInitializer.class);
		service = injector.getInstance(ListnowService.class);
	}

	@Test
	public void getAllUsers() {
		List<User> users;
		users = service.getAllUser();

//		assertEquals(1, users.size());
	}

	@Test
	public void getUser() {
		User user = service.getUserByEmail("taloto07@gmail.com");
		List<Group> groups = user.getGroups();

		assertNotEquals(0, groups.size());
		assertNotNull(user);
	}

	@Test
	public void addUser() {
		User u = new User();
		u.setEmail("chamnaplim18@yahoo.com");
		u.setFirstName("cham");
		u.setLastName("lim");
		u.setPassword("test");

		List<Group> lg = service.getAllGroup();
		u.setGroups(lg);

		// add user to database
		service.addUser(u);

		User fromDatabase = service.getUserByEmail("chamnaplim18@yahoo.com");

		assertEquals(u, fromDatabase);

		// remove tested added user
		service.removeUserByEmail("chamnaplim18@yahoo.com");
	}

	@Test
	public void addExistedUser() {
		User u = new User();
		u.setEmail("taloto07@gmail.com");
		u.setFirstName("cham");
		u.setLastName("lim");
		u.setPassword("test");
		
		boolean flag = service.addUser(u);
		
		assertFalse(flag);
	}

	@AfterClass
	public static void tearDownClass() {

		injector = null;
		service = null;
	}
}
