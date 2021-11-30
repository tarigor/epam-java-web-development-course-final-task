package com.epam.hotel.controller.filter;

import com.epam.hotel.entity.User;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.types.UserType;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provides the functionality of the menu initialization based on the logged user.
 */
public class MenuInitializer implements Filter {
    private static final Logger LOGGER = Logger.getLogger(MenuInitializer.class);
    private SiteMenuServiceImpl siteMenuService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (ServiceFactory.getInstance().isExceptionWhileInit()) {
            request.setAttribute("errorMessage", "A CRITICAL ERROR OCCURRED");
            try {
                servletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                response.getWriter().print("<html><head><title>A Critical Error Has Happened!</title></head>");
                response.getWriter().print("<body>A Critical Error Has Happened!</body>");
                response.getWriter().println("</html>");
                LOGGER.error(e);
            }
        }

        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            UserType userRole = loggedUser.getUserType();
            if (userRole.equals(UserType.ADMIN)) {
                request.getSession().setAttribute("menuList", SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(
                        MenuRole.COMMON,
                        MenuRole.ADMIN_LOGGED,
                        MenuRole.ANYONE_LOGGED));
            } else if (userRole.equals(UserType.CLIENT)) {
                request.getSession().setAttribute("menuList", SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(
                        MenuRole.COMMON,
                        MenuRole.CLIENT_LOGGED,
                        MenuRole.ANYONE_LOGGED));
            }
        } else {
            LOGGER.info("It is anyone user has been logged");
            request.getSession().setAttribute("menuList", SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(
                    MenuRole.COMMON,
                    MenuRole.ANYONE_NOT_LOGGED));
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
