package com.epam.hotel.controller.filter;

import com.epam.hotel.command.factory.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class provides the methods of handling request filtering in depends on the receiving command
 * from the web page before servlet initialization and implements the {@link Filter} interface.
 */
public class CommandFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CommandFilter.class);
    public static final String COMMAND_NAME = "name";
    public static final String CLASS_NAME = "className";
    private CommandFactory commandFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commandFactory = CommandFactory.getInstance();
        System.out.println("i'm in init method of command filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("i'm in filter command");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        String command = request.getParameter(COMMAND_NAME).toUpperCase();
        request.setAttribute(COMMAND_NAME, command);
        logger.info(String.format("The following command has been detected - /%s", command));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
