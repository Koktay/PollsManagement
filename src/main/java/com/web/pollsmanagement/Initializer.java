package com.web.pollsmanagement;

import com.web.pollsmanagement.util.AppServletContext;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class Initializer implements ServletContextInitializer {

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
		servletContext.setInitParameter("primefaces.THEME", "bootstrap");
		AppServletContext.getInstance().init(servletContext);
	}

}
