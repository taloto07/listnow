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

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.listnow.hosting.dao.Group;
import com.listnow.hosting.dao.User;

public class DispatchSignup extends BaseServlet {
	private static final long serialVersionUID = 1L;
       

    public DispatchSignup() {
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
		ST body = templates.getInstanceOf("signup");

		body.add("contextPath", contextPath);
		
		this.checkUserLogin(request, page);
		page.add("contextPath", contextPath);
		page.add("title", "Signup");
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
		// validate form
		UserForm validation = checkValidateUserInput(request);
		
		if (validation.isInValid()){	// form field is invlaid
			body = templates.getInstanceOf("signup");
			body.add("validateForm", validation.getErrorMessage());
			body.add("email", validation.email);
			body.add("firstName", validation.firstName);
			body.add("lastName", validation.lastName);
			body.add("contextPath", contextPath);
		}else{	// valid form add user to database
			boolean flag = addUserToDatabase(validation);
			if (flag == true){	// successfully added user to database
				body = templates.getInstanceOf("signupsuccess");
				body.add("firstName", this.changeFirstLetterToUpercase(validation.firstName));
				body.add("lastName", this.changeFirstLetterToUpercase(validation.lastName));
			}else{	// user already existed in database
				body = templates.getInstanceOf("signup");
				body.add("validateForm", "Email already existed! Please use different email");
				body.add("firstName", validation.firstName);
				body.add("lastName", validation.lastName);
				body.add("contextPath", contextPath);
			}
		}
		
		page.add("contextPath", contextPath);
		page.add("title", "Signup");
		page.add("body", body.render());
		
		out.print(page.render());
		out.flush();
	}
	
	protected void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

	
	private UserForm checkValidateUserInput(HttpServletRequest request) {
		UserForm form = new UserForm();
		
		form.email = request.getParameter("email");
		form.password = request.getParameter("password");
		form.firstName = request.getParameter("firstName");
		form.lastName = request.getParameter("lastName");
		
		return form;
	}
	
	private boolean addUserToDatabase(UserForm user){
		User u = new User();
		u.setEmail(user.email);
		u.setFirstName(user.firstName);
		u.setLastName(user.lastName);
		u.setPassword(convertToSHA256(user.password));
		
		// add user to user group
		List<Group> groups = new ArrayList<Group>();
		groups.add(service.getGroupByName("user"));
		
		u.setGroups(groups);
		boolean flag = service.addUser(u);
		if (!flag) return false;
		
		return true;
	}
	
	private String convertToSHA256(String password){
		return Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
	}
	
	private class UserForm{
		public String email;
		public String password;
		public String firstName;
		public String lastName;
		
		public UserForm(){
			email = null;
			password = null;
			firstName = null;
			lastName = null;
		}
		public boolean isInValid(){
			return (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty());
		}
		
		public String getErrorMessage(){
			String result = null;
			
			if (email.isEmpty()){
				result = "Email is empty.";
			}
			
			if (password.isEmpty()){
				if (result == null)
					result = "Password is empty.";
				else
					result += "<br/>Password is empty";
			}
			
			if (firstName.isEmpty()){
				if (result == null)
					result = "First Name is empty.";
				else
					result += "<br/>First Name is empty";
			}
			
			if (lastName.isEmpty()){
				if (result == null)
					result = "Last Name is empty.";
				else
					result += "<br/>Last Name is empty";
			}
			
			return result;
		}
	}

}
