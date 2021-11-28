package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class AccountCommand extends BaseCommand implements Command {
    private static final String ACCOUNT_PAGE = "account";
    private static final String INVOICE_PAGE = "invoice";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        boolean orderPrepare = Boolean.parseBoolean(request.getParameter("orderPrepare"));
        System.out.println("roomID->" + request.getParameter("roomID"));
        User user = updateClient(request);
        if (orderPrepare) {
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            request.setAttribute("roomID", roomID);
            request.setAttribute("orderID",request.getParameter("orderID"));
            request.setAttribute("requestID",request.getParameter("requestID"));
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
            doRedirect(request, response, ACCOUNT_PAGE);
        }
    }

    private User updateClient(HttpServletRequest request) {
        User client = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", clientService.getClient(client.getUserID()));
        return client;
    }
}
