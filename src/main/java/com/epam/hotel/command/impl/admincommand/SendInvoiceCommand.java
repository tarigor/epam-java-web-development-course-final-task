package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the preparation before execution of the {@link AdminCabinetCommand} command.
 */
public class SendInvoiceCommand extends BaseCommand implements Command {

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
        String[] singleRoomsSelected = request.getParameterValues("singleRoomsSelected");
        String[] doubleRoomsSelected = request.getParameterValues("doubleRoomsSelected");
        String[] suiteRoomsSelected = request.getParameterValues("suiteRoomsSelected");
        String[] deluxeRoomsSelected = request.getParameterValues("deluxeRoomsSelected");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        long clientID = Long.parseLong(request.getParameter("clientID"));
        int requestID = Integer.parseInt(request.getParameter("requestID"));

        bookService.insertNewOrder(clientID, requestID, singleRoomsSelected, doubleRoomsSelected, suiteRoomsSelected, deluxeRoomsSelected, dateFrom, dateTo);
        response.sendRedirect(request.getContextPath() + "/command?name=admin_cabinet");
    }
}
