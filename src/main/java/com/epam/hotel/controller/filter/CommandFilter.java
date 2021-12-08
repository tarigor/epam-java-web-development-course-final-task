package com.epam.hotel.controller.filter;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.factory.CommandFactory;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provides the functionality  of handling of the request filtering in depends on the receiving command
 * from the web page before servlet initialization.
 */
public class CommandFilter extends BaseCommand implements Filter {
    public static final String COMMAND_NAME = "name";
    public static final String NOT_AUTHORIZED_ACCESS = "error.not.authorized.access";
    public static final String ERROR_SERVICE_SCOPE = "error.service.scope";
    public static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
    private static final Logger LOGGER = Logger.getLogger(CommandFilter.class);
    private CommandFactory commandFactory;

    @Override
    public void init(FilterConfig filterConfig) {
        commandFactory = CommandFactory.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(COMMAND_NAME).toUpperCase();
        request.setAttribute(COMMAND_NAME, command);
        LOGGER.info(String.format("The following command has been detected - /%s", command));

        try {
            if (checkCommandRole(request, command)) {
                filterChain.doFilter(request, response);
            } else {
                showErrorPage(servletRequest, request, response, NOT_AUTHORIZED_ACCESS);
            }
        } catch (ServiceException e) {
            showErrorPage(servletRequest, request, response, ERROR_SERVICE_SCOPE);
        }
    }

    private void showErrorPage(ServletRequest servletRequest, HttpServletRequest request, HttpServletResponse response, String notAuthorizedAccess) throws IOException {
        request.setAttribute("errorMessage", notAuthorizedAccess);
        try {
            servletRequest.getRequestDispatcher(request.getContextPath() + ERROR_JSP).forward(request, response);
        } catch (ServletException | IOException e) {
            response.getWriter().print("<html><head><title>Critical Error Has Happened!</title></head>");
            response.getWriter().print("<body>Critical Error Has Happened!</body>");
            response.getWriter().println("</html>");
            LOGGER.error(e);
        }
    }

    private boolean checkCommandRole(HttpServletRequest req, String command) throws ServiceException {
        String commandRole = CommandFactory.getInstance().getCommandRole(command);
        LOGGER.info(String.format("command role detected -> %s", commandRole));
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            LOGGER.info(String.format("user role detected -> %s", user.getUserType().name()));
        }
        if (commandRole.contains("ANYONE")) {
            return true;
        }
        assert user != null;
        return commandRole.contains(user.getUserType().name());
    }

    @Override
    public void destroy() {

    }

}
