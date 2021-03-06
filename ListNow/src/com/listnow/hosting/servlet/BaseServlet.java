package com.listnow.hosting.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.google.inject.Inject;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.listnow.hosting.dao.User;
import com.listnow.hosting.service.ListnowService;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Inject
	ListnowService service;
    public BaseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected String getContextPath(){
		String contextPath = getServletContext().getContextPath();
		if (!contextPath.endsWith("/")) 
			contextPath = contextPath.concat("/");
		return contextPath;
	}
	
	protected STGroup getSTGroup(){
		String path = getServletContext().getRealPath("/WEB-INF/templates/");
		return new STGroupDir(path, '$', '$');
	}
	
	protected String compressHTML(String content){
		HtmlCompressor compressor = new HtmlCompressor();
		return compressor.compress(content);
	}

	protected void checkUserLogin(HttpServletRequest request, ST page){
		String user = request.getRemoteUser();
		
		if (user != null){
			User u = service.getUserByEmail(user);
			page.add("user", u.getFirstName() + " " + u.getLastName());
		}
	}
	
	protected String changeFirstLetterToUpercase(String string){
		return string.replace(string.charAt(0), String.valueOf(string.charAt(0)).toString().toUpperCase().charAt(0));
	}
}
