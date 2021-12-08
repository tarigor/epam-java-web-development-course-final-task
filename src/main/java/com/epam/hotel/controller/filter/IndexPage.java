package com.epam.hotel.controller.filter;

import com.epam.hotel.command.BaseCommand;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexPage extends BaseCommand implements Filter {
    private static final Object INDEX_PAGE = "index";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.getSession().setAttribute("lastpage", INDEX_PAGE);
        request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", INDEX_PAGE)).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
