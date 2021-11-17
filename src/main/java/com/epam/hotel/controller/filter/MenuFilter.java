package com.epam.hotel.controller.filter;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * The class provides the methods of handling request filtering in depends on navigation command receiving
 * from the menu of the web page before servlet initialization and implements the {@link Filter} interface.
 */
public class MenuFilter extends BaseCommand implements Filter {
    public static final String NAME = "name";
    public static final String DEFAULT_LANGUAGE = "en_US";
    private static Logger logger = Logger.getLogger(MenuFilter.class);
    private SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (attributesMap.size() != 0) {
            for (Map.Entry<String, Boolean> entry : attributesMap.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
        }

        String language = request.getParameter("lang");
        if (language == null) {
            request.getSession().setAttribute("language", DEFAULT_LANGUAGE);
        } else {
            request.getSession().setAttribute("language", language);
        }

        String pageName = request.getParameter(NAME);
        if (pageName == null) {
            pageName = "index";
        }

        logger.info(String.format("Redirected to %s", pageName));
        request.getSession().setAttribute("lastpage", pageName);
        request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", pageName)).forward(request, response);
        attributesMap.clear();
    }

    @Override
    public void destroy() {

    }
}
