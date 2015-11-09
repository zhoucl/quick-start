package com.shijie99.basic.boot;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");  
        servletContext.addListener(ContextLoaderListener.class);
        
		super.onStartup(servletContext);
		
		Dynamic regist = servletContext.addFilter("encoding", CharacterEncodingFilter.class);
		regist.setInitParameter("encoding", "UTF-8");
		regist.setInitParameter("forceEncoding", "true");
		regist.setAsyncSupported(true);
		regist.addMappingForUrlPatterns(null, true, "/");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
