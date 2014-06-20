package com.listnow.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

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
		ST body = templates.getInstanceOf("search");

		body.add("contextPath", contextPath);
		
		page.add("contextPath", contextPath);
		page.add("title", "Search");
		this.checkUserLogin(request, page);
		page.add("body", body.render());

		out.print(page.render());
		out.flush();
	}

}
