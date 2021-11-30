package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the functionality of the order removing by admin.
 */
public class RemoveRoomFromBooking extends BaseCommand implements Command {
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
//        int orderID = Integer.parseInt(request.getParameter("orderID"));
//        int roomID = Integer.parseInt(request.getParameter("roomID"));
//
//        User loggedUser = (User) request.getSession().getAttribute("user");
//        clientService.removeOrderFromBooking(orderID, roomID);
//        request.getSession().setAttribute("clientOrders", clientService.getClientOrders(loggedUser));
//        response.sendRedirect("page?name=" + CLIENT_CABINET);
    }
}
