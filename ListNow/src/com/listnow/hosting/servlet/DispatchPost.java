package com.listnow.hosting.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.listnow.hosting.dao.Category;
import com.listnow.hosting.dao.City;
import com.listnow.hosting.dao.Group;
import com.listnow.hosting.dao.Image;
import com.listnow.hosting.dao.Item;
import com.listnow.hosting.dao.ItemImage;
import com.listnow.hosting.dao.User;

public class DispatchPost extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public DispatchPost() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set unicode to utf-8 for internation
		response.setCharacterEncoding("UTF-8");

		// set response type to text/html
		response.setContentType("text/html");

		// Get PrintWriter to write back to client
		PrintWriter out = response.getWriter();

		// Get contextPath for any external files such as css, js path
		String contextPath = getContextPath();

		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("post");

		List<City> cities = service.getAllCitySort();
		List<Category> categories = service.getAllCategorySort();

		body.add("contextPath", contextPath);
		body.add("cities", cities);
		body.add("categories", categories);

		page.add("contextPath", contextPath);
		page.add("title", "Post");
		this.checkUserLogin(request, page);
		page.add("body", body.render());

		out.print(page.render());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// set unicode to utf-8 for internation
		response.setCharacterEncoding("UTF-8");

		// set response type to text/html
		response.setContentType("text/html");

		// Get PrintWriter to write back to client
		PrintWriter out = response.getWriter();

		// Get contextPath for any external files such as css, js path
		String contextPath = getContextPath();

		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = null;
		
		Post form = null;
		
		// proccess form to get all fields and store in form variable
		try {
			form = executeFormFile(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (form == null){	// this isn't multipart form
			body = templates.getInstanceOf("post");
			body.add("errorMessage", "Form is not a multiform!");
		}else if (!form.isValid()){	// is not valid form
			body = templates.getInstanceOf("post");
			body.add("errorMessage", form.getErrorMessage());
			body.add("title", form.title);
			body.add("description", form.description);
			body.add("price", form.price);
			
			City city = service.getCityByZipcode(Integer.parseInt(form.city));
			List<City> cities = service.getAllCitySort();
			cities.remove(city);
			body.add("city", city);
			body.add("cities", cities);
			
			Category category = service.getCategoryById(Integer.parseInt(form.category));
			List<Category> categories = service.getAllCategorySort();
			categories.remove(category);
			body.add("category", category);
			body.add("categories", categories);
			
		}else{ // is valid form, add to database
			try {
				addToDatabase(form, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			body = templates.getInstanceOf("postsuccess");
			body.add("message", "Posted Successfully!!!");
		}
		
		body.add("contextPath", contextPath);
		
		page.add("contextPath", contextPath);
		page.add("title", "Post");
		this.checkUserLogin(request, page);
		page.add("body", body.render());

		out.print(page.render());
		out.flush();
		
	}

	protected void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void addToDatabase(Post form, HttpServletRequest request) throws Exception{
		List<FileItem> files = form.items;
		
		// gets absolute path of the web application
		String applicationPath = getServletContext().getRealPath("");
		
		// constructs path of the directory to save uploaded file
		String uploadFilePath = applicationPath + File.separator + "image_post";
		
		// creates the save directory if it does not exists
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()){
			fileSaveDir.mkdir();
		}
		
		Item item = new Item();
		item.setTitle(form.title);
		item.setDescription(form.description);
		item.setPrice(Double.parseDouble(form.price));
		item.setCity(service.getCityByZipcode(Integer.parseInt(form.city)));
		item.setCategory(service.getCategoryById(Integer.parseInt(form.category)));
		item.setUser(service.getUserByEmail(request.getRemoteUser()));
		
		service.addItem(item);
		
		for (FileItem file: files){
			if (!file.getName().isEmpty()){ // user add this file
				Image image = new Image();
				
				// add image to database
				String fileName = file.getName(); 
				String extension = fileName.substring(fileName.lastIndexOf("."));
				
				image.setName(fileName);
				image.setPath("image_post");
				image.setExtension(extension);
				image.setUploadDate(new Date());
				service.addImage(image);	

				// set image table with item_image table and item table
				ItemImage itemImage = new ItemImage();
				itemImage.setItem(item);
				itemImage.setImage(image);
				service.addItemImage(itemImage);	
				
				// get actual file to store on file system
				String actualFileName = image.getId() + extension;
				
				// store image on file system
				String filePath = uploadFilePath + File.separator + actualFileName;
				File fileImage = new File(filePath);
				file.write(fileImage);	
			}
		}
		
	}
	
	protected Post executeFormFile(HttpServletRequest request) throws FileUploadException{
		// gets absolute path of the web application
		String applicationPath = getServletContext().getRealPath("");
		
		// constructs path of the directory to save uploaded file
		String uploadFilePath = applicationPath + File.separator + "image_post";
		
		// creates the save directory if it does not exists
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()){
			fileSaveDir.mkdir();
		}
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart){ // is not a multipart form
			return null;
		}
		
		Map<String, String> inputField = new HashMap<String, String>();
		List<FileItem> items = new ArrayList<FileItem>(); 
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(fileSaveDir);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		List<FileItem> fileItems = upload.parseRequest(request);
		Iterator<FileItem> iterator = fileItems.iterator();
		
		while(iterator.hasNext()){
			FileItem item = iterator.next();
			
			if (item.isFormField()){ // input field
				inputField.put(item.getFieldName(), item.getString());
			}else{ // file field
				items.add(item);
			}
		}
		
		Post post = new Post();
		post.title = inputField.get("title");
		post.description = inputField.get("description");
		post.price = inputField.get("price");
		post.city = inputField.get("city");
		post.category = inputField.get("category");
		post.items = items;
		
		return post;
	}
	
	private class Post{
		String title;
		String description;
		String price;
		String city;
		String category;
		List<FileItem> items;
		Post(){
			price= title = description = city = category = null;
		}
		
		public boolean isValid(){
			return (!price.isEmpty() && !title.isEmpty());
		}
		
		public String getErrorMessage(){
			String errorMessage = null;
			
			if (title.isEmpty()){
				errorMessage = "Title is empty. <br/>";
			}
			
			if (price.isEmpty()){
				if (errorMessage == null)
					errorMessage = "Price is empty.";
				else
					errorMessage += "Price is empty.";
			}
			return errorMessage;
		}
	}
	
	
}
