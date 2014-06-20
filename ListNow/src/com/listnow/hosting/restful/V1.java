package com.listnow.hosting.restful;

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
	
	@Path("/getallcity")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCity(){
		List<City> cities = service.getAllCity();
		Gson gson = new Gson();
		
		Map<String, List<City>> allCity = new HashMap<String, List<City>>(); 
		
		allCity.put("cities", cities);
		
		return Response.status(200).entity(gson.toJson(allCity)).build();
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
