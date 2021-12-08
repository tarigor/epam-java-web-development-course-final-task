package com.epam.hotel.command;

import com.epam.hotel.service.CommonSiteActivityService;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Provides the fields declaration which can be used by child classes.
 */
public abstract class BaseCommand {
    protected static final CommonSiteActivityService commonSiteActivityService =
            (CommonSiteActivityServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.COMMON_SITE_ACTIVITY_SERVICE);
    protected static final ClientServiceImpl clientService =
            (ClientServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.CLIENT_SERVICE);
    protected static final RoomServiceImpl roomService =
            (RoomServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.ROOM_SERVICE);
    protected static final BookServiceImpl bookService =
            (BookServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.BOOK_SERVICE);
    protected static final AdminServiceImpl adminService =
            (AdminServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.ADMIN_SERVICE);
    private static final Logger LOGGER = Logger.getLogger(BaseCommand.class);
    protected static HashMap<String, Object> attributesMap = new HashMap<>();

    /**
     * Provides a forwarding to the specific web page.
     *
     * @param request
     * @param response
     * @param pageName to be forwarded to.
     * @throws IOException when an input or output error is detected when the servlet handles the request.
     */
    protected void doRedirect(HttpServletRequest request, HttpServletResponse response, String pageName) throws IOException {
        try {
            storeAttributesInMap(request);
            request.getSession().setAttribute("lastpage", pageName);
            request.getRequestDispatcher(request.getContextPath() + String.format("/WEB-INF/jsp/%s.jsp", pageName)).forward(request, response);
        } catch (ServletException e) {
            response.getWriter().print("<html><head><title>A Critical Error Has Happened!</title></head>");
            response.getWriter().print("<body>A Critical Error Has Happened!</body>");
            response.getWriter().println("</html>");
            LOGGER.error(e);
        }
    }

    private void storeAttributesInMap(HttpServletRequest request) {
        Enumeration<String> e = request.getAttributeNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            Object value = request.getAttribute(name);
            attributesMap.put(name, value);
            request.getSession().setAttribute("attributesMap", attributesMap);
        }
    }
}
