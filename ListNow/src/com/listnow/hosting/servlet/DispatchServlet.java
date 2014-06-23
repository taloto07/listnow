package com.listnow.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.listnow.hosting.dao.City;

public class DispatchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public DispatchServlet() {
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
		ST page = templates.getInstanceOf("home");
		
		List<City> cities = service.getAllCitySort();
		
		page.add("contextPath", contextPath);
		page.add("cities", cities);

		out.print(page.render());
		out.flush();
	}

}
