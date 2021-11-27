package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class RequestCommand extends BaseCommand implements Command {
    private static final String LOGIN_PAGE = "login";
    private static final String CLIENT_CABINET = "clientcabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        int persons = Integer.parseInt(request.getParameter("persons"));
        String roomClass = request.getParameter("roomClass");
        String[] dateRange = getDateRange(request);
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("persons->" + persons + " roomClass->" + roomClass + " dateFrom->" + dateRange[0] + " dateTo->" + dateRange[1]);
        if (user != null) {
            clientService.insertRequest(user.getUserID(), persons, roomClass, dateRange[0], dateRange[1]);
            request.setAttribute("clientRequests", clientService.getClientRequests(user.getUserID()));
            request.setAttribute("clientOrders", clientService.getClientOrders(user));
            doRedirect(request, response, CLIENT_CABINET);
        } else {
            request.setAttribute("clientRequest", clientService.createRequest(persons,roomClass,dateRange[0],dateRange[1]));
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
