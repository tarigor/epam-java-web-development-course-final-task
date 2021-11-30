package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides preparation for the presentation of the "admin cabinet" page while handling of the client's order.
 */
public class OrderHandlingCommand extends BaseCommand implements Command {
    private static final String ADMIN_CABINET = "admincabinet";

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
        String actionType = request.getParameter("type");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        adminService.changeOrderStatusAdminAction(actionType, orderID, roomID);
        request.setAttribute("clientOrders", adminService.getAllOrders());
        doRedirect(request, response, ADMIN_CABINET);
    }
}
