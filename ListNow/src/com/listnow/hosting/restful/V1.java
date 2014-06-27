package com.listnow.hosting.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.inject.Singleton;
import com.listnow.hosting.dao.City;
import com.listnow.hosting.dao.User;
import com.listnow.hosting.guice.InjectorGuice;
import com.listnow.hosting.service.ListnowService;

@Singleton
@Path("/v1")
public class V1 {
	
	ListnowService service;
	
	public V1(){
		service = InjectorGuice.injector.getInstance(ListnowService.class);
	}
	
	@Path("/status")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String status(){
		return "API Version 1.0";
	}
	
	@Path("/test")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String test(){
		return "This is test string";
	}
	
	@Path("/getallcity")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCity(){
		List<City> cities = service.getAllCity();
		// too much object causes stackoverflow
		// need to seperate to get only city only
		List<City> actualCities = new ArrayList<City>(); 
		City city;
		for (City c: cities){
			city = new City();
			city.setZipcode(c.getZipcode());
			city.setName(c.getName());
			actualCities.add(city);
		}
		Gson gson = new Gson();
		
		Map<String, List> allCity = new HashMap<String, List>(); 
		
		allCity.put("cities", actualCities);
		
		return Response.status(200).entity(gson.toJson(allCity)).build();
	}
	
	@Path("getalluser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUser(){
		List<User> users = service.getAllUser();
		List<User> actualUsers = new ArrayList<User>();
		User user;
		for (User u: users){
			user = new User();
			user.setId(u.getId());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			actualUsers.add(user);
		}
		
		Gson gson = new Gson();
		
		Map<String, List> allUser = new HashMap<String, List>();
		allUser.put("users", actualUsers);
		
		return Response.status(200).entity(gson.toJson(allUser)).build();
	}
	
	@Path("/getcity/{cityname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCityByName(@PathParam("cityname") int zipecode){
		City city = service.getCityByZipcode(zipecode);
		
		if (city == null){
			return Response.status(406).build();
		}
		
		Map<String, City> cityMap = new HashMap<String, City>();
		cityMap.put("City", city);
		
		return Response.status(200).entity(new Gson().toJson(cityMap)).build();
	}
}
