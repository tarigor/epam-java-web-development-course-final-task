package com.epam.hotel.controller.filter;

import com.epam.hotel.entity.HttpCommand;
import com.epam.hotel.service.factory.ServiceFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;

public class CommandFilter implements Filter {
    private final String COMMAND = "command";
    private ServiceFactory serviceFactory;
    private HashMap<String, HttpCommand> commandHashMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //  commandHashMap = ((JsonFileService) serviceFactory.getService(ServiceType.JSON_FILE_SERVICE)).getMapOfCommandFromJson();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
