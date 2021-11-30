package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the functionality of the payment of order and returning back to "invoice" page.
 */
public class PayCommand extends BaseCommand implements Command {
    private static final String INVOICE_PAGE = "invoice";

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
        long clientID = Long.parseLong(request.getParameter("clientID"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int requestID = Integer.parseInt(request.getParameter("requestID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
        bookService.payInvoice(clientID, orderID, requestID, roomID, roomPrice);
        request.setAttribute("paid", true);
        updateClient(request);
        doRedirect(request, response, INVOICE_PAGE);
    }

    private User updateClient(HttpServletRequest request) throws ServiceException {
        User client = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", clientService.getClient(client.getUserID()));
        return client;
    }
}
