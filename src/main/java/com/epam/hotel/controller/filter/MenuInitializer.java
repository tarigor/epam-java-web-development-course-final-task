package com.epam.hotel.controller.filter;

import com.epam.hotel.entity.MenuRole;
import com.epam.hotel.entity.User;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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

        User authorizedUser = (User) request.getSession().getAttribute("authorizedUser");
        if (authorizedUser != null) {
            if (authorizedUser.getUserType().contains("ADMIN")) {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ADMIN_LOGGED, MenuRole.ANYONE_LOGGED));
            } else if (authorizedUser.getUserType().contains("CLIENT")) {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.USER_LOGGED, MenuRole.ANYONE_LOGGED));
            }
        } else {
            logger.info("It is anyone user has been logged");
            request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ANYONE_NOT_LOGGED));
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
