package com.stickify.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Modified on: 18 July 2012
 * 
 * Initializer replacement for web.xml
 *
 * @version: 1.0 18 July 2012
 * @author (c): Michael Le
 * 
 */
public class StickifyInitializer implements WebApplicationInitializer {
	
	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.scan("com.stickify.config");
		
		// Manages the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//Enable OpenEntityManagerInView pattern
		servletContext.addFilter("openEntityManagerInViewFilter", new OpenEntityManagerInViewFilter()).addMappingForUrlPatterns(null, false, "/*");
		// Secures the application
		servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
		
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
	}
}
