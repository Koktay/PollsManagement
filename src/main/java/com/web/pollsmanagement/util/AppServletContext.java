package com.web.pollsmanagement.util;

import javax.servlet.ServletContext;

public final class AppServletContext {

    private static AppServletContext instance = null;

    private ServletContext servletContext = null;

    private AppServletContext() {}

    public static AppServletContext getInstance() {
        if (instance == null) {
            instance = new AppServletContext();
        }
        return instance;
    }

    public void init(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }


}
