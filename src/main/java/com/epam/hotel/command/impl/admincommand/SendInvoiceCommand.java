package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class SendInvoiceCommand extends BaseCommand implements Command {
    private static final String LOGIN_PAGE = "login";
    private static final String USER_PAGE = "clientcabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        String[] singleRoomsSelected = request.getParameterValues("singleRoomsSelected");
        String[] doubleRoomsSelected = request.getParameterValues("doubleRoomsSelected");
        String[] suiteRoomsSelected = request.getParameterValues("suiteRoomsSelected");
        String[] deluxeRoomsSelected = request.getParameterValues("deluxeRoomsSelected");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        long clientID = Long.parseLong(request.getParameter("clientID"));
        int requestID = Integer.parseInt(request.getParameter("requestID"));

        bookService.insertNewOrder(clientID, requestID, singleRoomsSelected, doubleRoomsSelected, suiteRoomsSelected, deluxeRoomsSelected, dateFrom, dateTo);
        response.sendRedirect("command?name=admin_cabinet");
    }
}
