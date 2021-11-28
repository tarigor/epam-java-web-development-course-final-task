package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class ChargeCommand extends BaseCommand implements Command {
    private static final String CHARGE_PAGE = "chargeaccount";
    private static final String ACCOUNT_PAGE = "account";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        if (request.getParameter("chargeAmount") != null) {
            double chargeAmount = Double.parseDouble(request.getParameter("chargeAmount"));
            long clientID = Long.parseLong(request.getParameter("userID"));
            System.out.println("chargeAmount->" + chargeAmount);
            clientService.chargeAccount(clientID, chargeAmount);
            response.sendRedirect("command?name=account&result=true");
        } else {
            doRedirect(request, response, CHARGE_PAGE);
        }
    }
}
