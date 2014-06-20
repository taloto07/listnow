package com.listnow.hosting.guice;

import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.listnow.hosting.servlet.DispatchLogin;
import com.listnow.hosting.servlet.DispatchLogout;
import com.listnow.hosting.servlet.DispatchSearch;
import com.listnow.hosting.servlet.DispatchServlet;
import com.listnow.hosting.servlet.DispatchSignup;
import com.listnow.hosting.servlet.LoginError;

public class MainModule extends ServletModule{
	@Override
	protected void configureServlets(){
		install(new JpaPersistModule("listnowJPA"));
		filter("/*").through(PersistFilter.class);
		
		bind(DispatchServlet.class).in(Singleton.class);
		bind(LoginError.class).in(Singleton.class);
		bind(DispatchSearch.class).in(Singleton.class);
		bind(DispatchSignup.class).in(Singleton.class);
		bind(DispatchLogin.class).in(Singleton.class);
		bind(DispatchLogout.class).in(Singleton.class);
		
		serve("/").with(DispatchServlet.class);
		serve("/secure").with(DispatchServlet.class);
		serve("/loginerror").with(LoginError.class);
		serve("/search").with(DispatchSearch.class);
		serve("/signup").with(DispatchSignup.class);
		serve("/login").with(DispatchLogin.class);
		serve("/logout").with(DispatchLogout.class);
	}
}
