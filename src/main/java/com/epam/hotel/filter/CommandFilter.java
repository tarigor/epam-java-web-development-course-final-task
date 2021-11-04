package com.epam.hotel.filter;

import com.epam.hotel.entity.HttpCommand;
import com.epam.hotel.service.factory.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String command = request.getParameter("command");
        if (command.contains("view")) {
            request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", command.replace("view", ""))).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
