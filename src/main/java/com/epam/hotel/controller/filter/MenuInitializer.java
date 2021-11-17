package com.epam.hotel.controller.filter;

import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuInitializer implements Filter {
    private static final Logger logger = Logger.getLogger(MenuInitializer.class);
    private SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            UserType userRole = loggedUser.getUserType();
            if (userRole.equals(UserType.ADMIN)) {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ADMIN_LOGGED, MenuRole.ANYONE_LOGGED));
            } else if (userRole.equals(UserType.CLIENT)) {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.USER_LOGGED, MenuRole.ANYONE_LOGGED));
            }
        } else {
            logger.info("It is anyone user has been logged");
            request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ANYONE_NOT_LOGGED));
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
