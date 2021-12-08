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
 * Provides preparation for the presentation of the "account" page or "invoice" page if there is a request to represent an invoice while order handling.
 */
public class AccountCommand extends BaseCommand implements Command {
    private static final String ACCOUNT_PAGE = "account";
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
        boolean orderPrepare = Boolean.parseBoolean(request.getParameter("orderPrepare"));
        User user = updateClient(request);
        if (orderPrepare) {
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            request.setAttribute("roomID", roomID);
            request.setAttribute("orderID", request.getParameter("orderID"));
            request.setAttribute("requestID", request.getParameter("requestID"));
            request.setAttribute("roomClass", request.getParameter("roomClass"));
            request.setAttribute("dateFrom", request.getParameter("dateFrom"));
            request.setAttribute("dateTo", request.getParameter("dateTo"));
            request.setAttribute("orderPrepare", true);
            double roomPrice = roomService.getRoomPrice(roomID);
            request.setAttribute("roomPrice", roomPrice);
            if (user.getAccount() < roomPrice) {
                request.setAttribute("notEnoughMoney", true);
            }
            doRedirect(request, response, INVOICE_PAGE);
        } else {
            if (request.getParameter("result") != null) {
                request.setAttribute("charged", true);
            }
            if (request.getParameter("paid") != null) {
                request.setAttribute("paid", true);
            }
            doRedirect(request, response, ACCOUNT_PAGE);
        }
    }

    private User updateClient(HttpServletRequest request) throws ServiceException {
        User client = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", clientService.getClient(client.getUserID()));
        return client;
    }
}
