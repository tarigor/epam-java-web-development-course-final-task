package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class PayCommand  extends BaseCommand implements Command {
    private static final String INVOICE_PAGE = "invoice";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
		long clientID = Long.parseLong(request.getParameter("clientID"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int requestID = Integer.parseInt(request.getParameter("requestID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
        bookService.payInvoice(clientID,orderID,requestID,roomID,roomPrice);
        request.setAttribute("paid",true);
        updateClient(request);
        doRedirect(request,response,INVOICE_PAGE);
    }
    private User updateClient(HttpServletRequest request) {
        User client = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", clientService.getClient(client.getUserID()));
        return client;
    }
}
