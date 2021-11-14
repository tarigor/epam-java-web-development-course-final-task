package com.epam.hotel.controller.filter;

import com.epam.hotel.entity.MenuRole;
import com.epam.hotel.entity.User;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The class provides the methods of handling request filtering in depends on navigation command receiving
 * from the menu of the web page before servlet initialization and implements the {@link Filter} interface.
 */
public class MenuFilter implements Filter {
    public static final String NAME = "name";
    private static Logger logger = Logger.getLogger(MenuFilter.class);
    private SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        User authorizedUser = (User) request.getSession().getAttribute("authorizedUser");
//        if (authorizedUser != null) {
//            if(authorizedUser.getUserType().contains("ADMIN")){
//                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON,MenuRole.ADMIN_LOGGED,MenuRole.ANYONE_LOGGED));
//            }else {
//                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON,MenuRole.USER_LOGGED,MenuRole.ANYONE_LOGGED));
//            }
//        } else {
//            request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON,MenuRole.ANYONE_NOT_LOGGED));
//        }

        String pageName = request.getParameter(NAME);
        logger.info(String.format("Redirected to %s", pageName));
        request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", pageName)).forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
