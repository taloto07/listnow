package com.listnow.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.listnow.hosting.dao.Category;
import com.listnow.hosting.dao.City;
import com.listnow.hosting.dao.Image;
import com.listnow.hosting.dao.Item;
import com.listnow.hosting.dao.ItemImage;
import com.listnow.hosting.dao.User;
import com.listnow.hosting.dao.UserImage;
import com.listnow.hosting.guice.MainModule;
import com.listnow.hosting.service.ListnowPersistenceInitializer;
import com.listnow.hosting.service.ListnowService;

public class ItemTest {
	static Injector injector;
	static ListnowService service;

	@BeforeClass
	public static void init() {
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(ListnowPersistenceInitializer.class);
		service = injector.getInstance(ListnowService.class);
	}
	
	@Test
	public void getAllItem() {
		List<Item> items = service.getAllItem();
	}
	
	@Test 
	public void addItem(){
		Item item = new Item();

		item.setTitle("Nexus 5");
		item.setDescription("Like new Nexus 5.");
		item.setPrice(300.00);
		
		City city = service.getCityByZipcode(98052); 
		item.setCity(city);
		
		Category category = service.getCategoryById(1);
		item.setCategory(category);
		
		User user = service.getUserByEmail("taloto07@gmail.com");
		item.setUser(user);

		
		boolean flag = service.addItem(item);
		
		assertTrue(flag);
		
		flag = service.removeItem(item);
		
		assertTrue(flag);
	}
	
//	@Test
	public void addItemWithImageAndUser(){
		Item item = new Item();
		item.setTitle("Nexus 5");
		item.setDescription("Like new Nexus 5.");
		item.setPrice(300.00);
		
		City city = service.getCityByZipcode(98052); 
		item.setCity(city);
		
		Category category = service.getCategoryById(1);
		item.setCategory(category);
		
		User u = service.getUserByEmail("taloto07@gmail.com");
		item.setUser(u);
		
		service.addItem(item);
		
		Image image = new Image();
		image.setUploadDate(new Date());
		image.setName("test image");
		image.setExtension(".jpg");
		
		service.addImage(image);
		
		ItemImage iImage = new ItemImage();
		iImage.setImage(image);
		iImage.setItem(item);
		
		service.addItemImage(iImage);
		
		service.removeImage(image);
		service.removeItem(item);
	}
	
	@Test
	public void searchItem(){
		// search for somthing exist in database
		List<Item> items = service.searchItemByTitle("iphone");
		assertNotEquals(0, items.size());
		
		for (Item item: items){
			if (item.getCity().getZipcode() == 98052){
				System.out.print(item.getTitle() + " : ");
				System.out.println(item.getItemImages().get(0).getImage().getId()+item.getItemImages().get(0).getImage().getExtension());
			}
		}
		
		// search for something doesn't exist in database
		items = service.searchItemByTitle("t-shirt");
		assertEquals(0, items.size());
		
		
	}
	
	@AfterClass
	public static void tearDownClass() {

		injector = null;
		service = null;
	}
}
