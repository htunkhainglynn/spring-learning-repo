package com.jdc.hello.initializer;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

// I do not need web-app.xml

public class MvcInitializer extends AbstractDispatcherServletInitializer {

	String SERVLET_CONTENT_NAME = "/WEB-INF/controller-config.xml";
	String ROOT_CONTENT_NAME = "/WEB-INF/root-config.xml";
	
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		var servletContent = new XmlWebApplicationContext();
		servletContent.setConfigLocation(SERVLET_CONTENT_NAME);
		return servletContent;
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		var rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocation(ROOT_CONTENT_NAME);
		return rootContext;
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
