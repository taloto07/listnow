package com.listnow.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.listnow.hosting.dao.UserImage;
import com.listnow.hosting.guice.MainModule;
import com.listnow.hosting.service.ListnowPersistenceInitializer;
import com.listnow.hosting.service.ListnowService;

public class UserImageTest {
	static Injector injector;
	static ListnowService service;

	@BeforeClass
	public static void init() {
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(ListnowPersistenceInitializer.class);
		service = injector.getInstance(ListnowService.class);
	}

	@Test
	public void getAllUserImage() {
		List<UserImage> userImage = service.getAllUserImage();
		
		assertEquals(0,userImage.size());
	}

	@AfterClass
	public static void tearDownClass() {

		injector = null;
		service = null;
	}
}
