package com.listnow.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class DispatchLogin extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public DispatchLogin() {
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
		ST body = null;
		
		if (request.getMethod().equalsIgnoreCase("post")){ // POST request
			if (request.getRemoteUser() == null){ // login fail
				body = templates.getInstanceOf("login");
				body.add("errorMessage", "Wrong Email or Password!");
			}else{ // login success
				body = templates.getInstanceOf("loginsuccess");
				body.add("message", "Successfully log in!");
			}
		}else{ // GET request
			if (request.getRemoteUser() == null)
				body = templates.getInstanceOf("login");
			else{
				body = templates.getInstanceOf("loginsuccess");
				body.add("message", "Successfully log in!");
			}
		}
		
		
		this.checkUserLogin(request, page);
		body.add("contextPath", contextPath);
		page.add("title", "Log In");
		page.add("contextPath", contextPath);
		page.add("body", body.render());

		out.print(page.render());
		out.flush();
	}

}
