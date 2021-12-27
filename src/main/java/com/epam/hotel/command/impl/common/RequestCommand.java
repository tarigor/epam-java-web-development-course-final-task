package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.EmailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the functionality of the request creation by a client.
 */
public class RequestCommand extends BaseCommand implements Command {
    private static final String LOGIN_PAGE = "login";
    private static final String CLIENT_CABINET = "clientcabinet";
    private final EmailServiceImpl emailService =
            (EmailServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.EMAIL_SERVICE);

    /**
     * Handles a GET or POST request received via HTTP from a WEB page.
     *
     * @param request  object that contains the request the client has made of the servlet.
     * @param response object that contains the response the servlet sends to the client.
     * @throws ServerException if the request could not be handled.
     * @throws IOException     when an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException, ServiceException {
        int persons = Integer.parseInt(request.getParameter("persons"));
        String roomClass = request.getParameter("roomClass");
        String[] dateRange = getDateRange(request);
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int requestID = clientService.insertRequest(user.getUserID(), persons, roomClass, dateRange[0], dateRange[1]);
            emailService.sendEmailToAdmin(user, requestID, persons, roomClass, dateRange[0], dateRange[1]);
            request.setAttribute("clientRequests", clientService.getClientRequests(user.getUserID()));
            request.setAttribute("clientOrders", clientService.getClientOrders(user));
            response.sendRedirect(request.getContextPath() + "/command?name=client_cabinet");
        } else {
            request.setAttribute("clientRequest", clientService.createRequest(persons, roomClass, dateRange[0], dateRange[1]));
            request.setAttribute("loginAndCompleteRequest", true);
            doRedirect(request, response, LOGIN_PAGE);
        }
    }

    private String[] getDateRange(HttpServletRequest request) {
        String dateFrom;
        String dateTo;
        if (request.getParameter("dateFrom") != null && request.getParameter("dateTo") != null) {
            dateFrom = request.getParameter("dateFrom");
            dateTo = request.getParameter("dateTo");
        } else {
            String[] dateRange = request.getParameter("datefilter").split(":");
            dateFrom = dateRange[0].trim();
            dateTo = dateRange[1].trim();
        }
        return new String[]{dateFrom, dateTo};
    }

}
