package com.epam.hotel.command;

import com.epam.hotel.service.CommonSiteActivityService;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.ClientServiceImpl;
import com.epam.hotel.service.impl.CommonSiteActivityServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected static HashMap<String, Boolean> attributesMap = new HashMap<>();
    protected static final RoomServiceImpl roomService =
            (RoomServiceImpl) ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);

    protected void doRedirect(HttpServletRequest request, HttpServletResponse response, String pageName) throws IOException {
        try {
            request.getSession().setAttribute("lastpage", pageName);
            request.getRequestDispatcher(String.format("WEB-INF/jsp/%s.jsp", pageName)).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
