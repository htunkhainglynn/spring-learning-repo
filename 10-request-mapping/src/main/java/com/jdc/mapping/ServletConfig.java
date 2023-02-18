package com.jdc.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebMvc
@ComponentScan("com.jdc.mapping.controller")
public class ServletConfig implements WebMvcConfigurer {
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/templates/").suffix(".jsp");
	}
	
	@Bean(name = "requestMappingHandlerMapping")
	public RequestMappingHandlerMapping mapping() {
		var mapping = new RequestMappingHandlerMapping();
		mapping.setRemoveSemicolonContent(false);
		return mapping;
	}
}
