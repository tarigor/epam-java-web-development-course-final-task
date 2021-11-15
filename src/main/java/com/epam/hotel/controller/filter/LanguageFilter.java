package com.epam.hotel.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String language = request.getParameter("type");
        request.getSession().setAttribute("language", language);
        String lastPage = (String) request.getSession().getAttribute("lastpage");
        if (lastPage == null) {
            lastPage = "index";
        }
        request.getRequestDispatcher(String.format("WEB-INF/jsp/%s.jsp", lastPage)).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
