package com.epam.hotel.command;

import com.epam.hotel.service.CommonSiteActivityService;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * An abstract class provides the filed declaration which can be used by child classes.
 */
public abstract class BaseCommand {
    protected static final CommonSiteActivityService commonSiteActivityService =
            (CommonSiteActivityServiceImpl) ServiceFactory.getInstance().getService(ServiceType.COMMON_SITE_ACTIVITY_SERVICE);
    protected static final PropertiesFileServiceImpl propertiesFileService =
            (PropertiesFileServiceImpl) ServiceFactory.getInstance().getService(ServiceType.PROPERTIES_FILE_SERVICE);
    protected static final ClientServiceImpl clientService =
            (ClientServiceImpl) ServiceFactory.getInstance().getService(ServiceType.CLIENT_SERVICE);
    protected static final RoomServiceImpl roomService =
            (RoomServiceImpl) ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
    protected static final BookServiceImpl bookService =
            (BookServiceImpl) ServiceFactory.getInstance().getService(ServiceType.BOOK_SERVICE);
    protected static final AdminServiceImpl adminService =
            (AdminServiceImpl) ServiceFactory.getInstance().getService(ServiceType.ADMIN_SERVICE);

    protected static HashMap<String, Object> attributesMap = new HashMap<>();


    protected void doRedirect(HttpServletRequest request, HttpServletResponse response, String pageName) throws IOException {
        try {
            storeAttributesInMap(request);
            request.getSession().setAttribute("lastpage", pageName);
            request.getRequestDispatcher(String.format("WEB-INF/jsp/%s.jsp", pageName)).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methods provides storing of the all attributes to keep content while language locale change during a page reload
     *
     * @param request
     */
    private void storeAttributesInMap(HttpServletRequest request) {
        Enumeration<String> e = request.getAttributeNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            Object value = request.getAttribute(name);
            attributesMap.put(name, value);
        }
    }
}
