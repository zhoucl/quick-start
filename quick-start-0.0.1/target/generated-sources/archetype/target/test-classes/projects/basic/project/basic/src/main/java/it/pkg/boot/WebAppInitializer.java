package it.pkg.boot;

import java.io.FileNotFoundException;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		try {
			Log4jConfigurer.initLogging("classpath:log4j.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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
