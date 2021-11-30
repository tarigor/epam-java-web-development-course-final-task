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
 * Provides a function of the request cancelling by client and returning to the "client cabinet" page.
 */
public class CancelRequestCommand extends BaseCommand implements Command {
    private static final String CLIENT_CABINET = "clientcabinet";

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
        int requestID = Integer.parseInt(request.getParameter("requestID"));
        User user = (User) request.getSession().getAttribute("user");
        clientService.removeRequest(requestID);
        request.setAttribute("clientOrders", clientService.getClientOrders(user));
        request.setAttribute("clientRequests", clientService.getClientRequests(user.getUserID()));
        doRedirect(request, response, CLIENT_CABINET);
    }
}
