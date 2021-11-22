package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class RemoveRoomFromBooking extends BaseCommand implements Command {
    private static final String CLIENT_CABINET = "clientcabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));

        User loggedUser = (User) request.getSession().getAttribute("user");
        clientService.removeOrderFromBooking(orderID, roomID);
        request.getSession().setAttribute("clientOrders", clientService.getClientOrders(loggedUser));
        response.sendRedirect("page?name=" + CLIENT_CABINET);
    }
}
