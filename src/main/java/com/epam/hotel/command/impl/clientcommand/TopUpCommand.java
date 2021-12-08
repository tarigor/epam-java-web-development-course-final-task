package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the functionality of top up of the client account
 */
public class TopUpCommand extends BaseCommand implements Command {
    private static final String TOP_UP_PAGE = "chargeaccount";

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
        if (request.getParameter("chargeAmount") != null) {
            double chargeAmount = Double.parseDouble(request.getParameter("chargeAmount"));
            long clientID = Long.parseLong(request.getParameter("userID"));
            System.out.println("chargeAmount->" + chargeAmount);
            clientService.topUpAccount(clientID, chargeAmount);
            response.sendRedirect(request.getContextPath() + "/command?name=account&result=true");
        } else {
            doRedirect(request, response, TOP_UP_PAGE);
        }
    }
}
