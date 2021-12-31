package com.epam.hotel.controller.filter;

import com.epam.hotel.command.BaseCommand;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides the functionality of handling of the request filtering in depends on navigation command receiving
 * from the menu of the web page before servlet initialization.
 */
public class MenuFilter extends BaseCommand implements Filter {
    public static final String NAME = "name";
    public static final String DEFAULT_LANGUAGE = "en_US";
    private static final Logger LOGGER = Logger.getLogger(MenuFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //for keeping content while language change and page reload
        HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("attributesMap");
        if (map != null) {
            if (map.size() != 0) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
            }
        }

        String language = request.getParameter("lang");
        if (language == null) {
            request.getSession().setAttribute("language", DEFAULT_LANGUAGE);
        } else {
            request.getSession().setAttribute("language", language);
        }

        String pageName = request.getParameter(NAME);
        if (pageName.equals("")) {
            pageName = "index";
        }

        LOGGER.info(String.format("Redirected to %s", pageName));
        request.getSession().setAttribute("lastpage", pageName);
        request.getRequestDispatcher(String.format("/WEB-INF/jsp/%s.jsp", pageName)).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
