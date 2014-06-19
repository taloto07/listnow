package com.listnow.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.listnow.hosting.dao.City;
import com.listnow.hosting.guice.MainModule;
import com.listnow.hosting.service.ListnowPersistenceInitializer;
import com.listnow.hosting.service.ListnowService;

public class CityTest {
	static Injector injector;
	static ListnowService service;
	
	@BeforeClass
	public static void init(){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(ListnowPersistenceInitializer.class);
		service = injector.getInstance(ListnowService.class);
	}
	
	@Test
	public void getAllCity() {
		List<City> cities;
		cities = service.getAllCity();
		
		assertNotNull(cities);
	}
	
	@Test
	public void getOneCity(){
		City city;
		
		city = service.getCityByZipcode(12345);
		assertNull(city);
		
		city = service.getCityByZipcode(98052);
		assertNotNull(city);
	}
	
	@AfterClass
	public static void tearDownClass(){
		injector = null;
		service = null;
	}

}
