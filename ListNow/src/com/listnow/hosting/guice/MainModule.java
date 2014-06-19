package com.listnow.hosting.guice;

import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.listnow.hosting.servlet.DispatchServlet;
import com.listnow.hosting.servlet.Login;
import com.listnow.hosting.servlet.LoginError;

public class MainModule extends ServletModule{
	@Override
	protected void configureServlets(){
		install(new JpaPersistModule("listnowJPA"));
		filter("/*").through(PersistFilter.class);
		
		bind(DispatchServlet.class).in(Singleton.class);
		bind(Login.class).in(Singleton.class);
		bind(LoginError.class).in(Singleton.class);
		
		serve("/").with(DispatchServlet.class);
		serve("/secure").with(DispatchServlet.class);
		serve("/login").with(Login.class);
		serve("/loginerror").with(LoginError.class);
	}
}
