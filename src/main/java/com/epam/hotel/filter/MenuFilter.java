package com.epam.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MenuFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String url = request.getRequestURI();
        System.out.println("url->" + url);
        request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", url.replace("/menu/", ""))).forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
