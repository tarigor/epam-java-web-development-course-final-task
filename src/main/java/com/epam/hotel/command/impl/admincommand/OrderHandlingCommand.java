package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class OrderHandlingCommand extends BaseCommand implements Command {
    private static final String ADMIN_CABINET = "admincabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        String actionType = request.getParameter("type");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        adminService.doActionWithOrder(actionType, orderID, roomID);
        request.setAttribute("clientOrders", adminService.getAllOrders());
        doRedirect(request, response, ADMIN_CABINET);
    }
}
