package com.epam.hotel.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MenuFilter implements Filter {
    private static Logger logger = Logger.getLogger(MenuFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String url = request.getRequestURI();
        logger.info(String.format("Redirected to %s", url));
        request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", url.replace("/menu/", ""))).forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
