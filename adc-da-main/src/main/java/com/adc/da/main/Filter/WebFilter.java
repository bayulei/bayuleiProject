package com.adc.da.main.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class WebFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(WebFilter.class);
    public WebFilter() {
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest)request);
        chain.doFilter(xssRequest, response);
    }

    public void destroy() {
    }

}
