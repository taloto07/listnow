package com.listnow.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.listnow.hosting.dao.Group;
import com.listnow.hosting.guice.MainModule;
import com.listnow.hosting.service.ListnowPersistenceInitializer;
import com.listnow.hosting.service.ListnowService;

public class GroupTest {
	static Injector injector;
	static ListnowService service;
	
	@BeforeClass
	public static void init(){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(ListnowPersistenceInitializer.class);
		service = injector.getInstance(ListnowService.class);
	}
	@Test
	public void getAllGroup(){
		List<Group> groups = service.getAllGroup();
		assertNotEquals(0, groups.size());
	}
	
	@Test
	public void getGroupByGroupName(){
		Group group = service.getGroupByName("admin");
		assertNotNull(group);
	}
	
	@Test
	public void addGroup(){
		Group group = new Group();
		group.setName("premium");
		group.setDescription("Premium can do more than user group, but less than admin group");
		boolean result = service.addGroup(group);
		
		assertTrue(result);
	}
	
	@AfterClass
	public static void tearDownClass(){
		service.removeGroupByName("premium");
		
		injector = null;
		service = null;
	}
}
