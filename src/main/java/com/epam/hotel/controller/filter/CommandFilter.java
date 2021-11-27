package com.epam.hotel.controller.filter;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.factory.CommandFactory;
import com.epam.hotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class provides the methods of handling request filtering in depends on the receiving command
 * from the web page before servlet initialization and implements the {@link Filter} interface.
 */
public class CommandFilter extends BaseCommand implements Filter {
    public static final String COMMAND_NAME = "name";
    public static final String CLASS_NAME = "className";
    private static final Logger logger = Logger.getLogger(CommandFilter.class);
    private CommandFactory commandFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commandFactory = CommandFactory.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        attributesMap.clear();

        String command = request.getParameter(COMMAND_NAME).toUpperCase();
        request.setAttribute(COMMAND_NAME, command);
        logger.info(String.format("The following command has been detected - /%s", command));


        if (checkCommandRole(request, command)) {
            filterChain.doFilter(request, response);
        } else {
            System.out.println("you are not authorized to do this");
            request.setAttribute("errorMessage", "not.authorized.access");
            try {
                servletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean checkCommandRole(HttpServletRequest req, String command) {
        String commandRole = CommandFactory.getInstance().getCommandRole(command);
        System.out.println("command role -> " + commandRole);
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            System.out.println("user role -> " + user.getUserType().name());
        }
        if (commandRole.contains("ANYONE")) {
            return true;
        }
        assert user != null;
        return commandRole.contains(user.getUserType().name());
    }

}
