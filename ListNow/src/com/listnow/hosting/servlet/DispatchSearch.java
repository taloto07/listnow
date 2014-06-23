package com.listnow.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.listnow.hosting.dao.City;
import com.listnow.hosting.dao.Item;

public class DispatchSearch extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public DispatchSearch() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void proccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		
		Parameter params = this.getSearchKey(request);
		
		List<Item> items = service.searchItemByTitle(params.searchKey);
		List<Item> resultItems = new ArrayList<Item>();
		if (items.size() > 0 && params.zipcode != 0){
			for (Item item: items){
				if (item.getCity().getZipcode() == params.zipcode)
					resultItems.add(item);
			}
		}else{
			resultItems = items;
		}
		
		ST body = templates.getInstanceOf("search");
		body.add("contextPath", contextPath);
		body.add("items", resultItems);
		
		page.add("contextPath", contextPath);
		page.add("title", "Search");
		this.checkUserLogin(request, page);
		page.add("body", body.render());

		out.print(this.compressHTML(page.render()));
		out.flush();
	}
	
	protected Parameter getSearchKey(HttpServletRequest request){
		Parameter params = new Parameter();
		params.searchKey = request.getParameter("searchKey");
		if (request.getParameter("city") != null && !request.getParameter("city").isEmpty())
			params.zipcode = Integer.parseInt(request.getParameter("city"));
		else
			params.zipcode = 0;
		
		return params;
	}
	
	private class Parameter{
		String searchKey;
		int zipcode;
		
		Parameter(){
			searchKey = null;
		}
		
	}

}
