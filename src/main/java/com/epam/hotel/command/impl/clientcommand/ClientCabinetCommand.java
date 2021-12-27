package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

/**
 * Provides preparation for the presentation of the "client cabinet" web page.
 */
public class ClientCabinetCommand extends BaseCommand implements Command {
    private static final String CLIENT_CABINET_PAGE = "clientcabinet";

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
        User user = (User) request.getSession().getAttribute("user");

        ArrayList<ClientRequest> clientRequests = clientService.getClientRequests(user.getUserID());
        ArrayList<ClientOrderRoom> clientOrders = clientService.getClientOrders(user);

        request.getSession().setAttribute("clientOrders", clientOrders);
        request.getSession().setAttribute("clientRequests", clientRequests);
        doRedirect(request, response, CLIENT_CABINET_PAGE);
    }
}
