package com.shijie99.basic.boot;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
//		String log4jConfiguration = System.getProperty("log4jConfiguration");
//		System.out.println("log4jConfiguration:" + log4jConfiguration);
//		if(null != log4jConfiguration && !"".equals(log4jConfiguration)) {
//			if(new File(log4jConfiguration).exists()) {
//				servletContext.setInitParameter("log4jConfiguration", "file://" + log4jConfiguration);
//			} else {
//				System.out.println(log4jConfiguration + " is not exist, use defaut log4jConfiguration");
//			}
//		}
		
        servletContext.addListener(Log4jServletContextListener.class);
        
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
