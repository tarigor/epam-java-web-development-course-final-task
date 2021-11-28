package com.epam.hotel.command.impl.clientcommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class MakePaymentCommand extends BaseCommand implements Command {
    private static final String PAYMENT_PAGE = "account";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        int roomID = Integer.parseInt(request.getParameter("requestID"));
        String roomClass = request.getParameter("roomClass");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        doRedirect(request, response, PAYMENT_PAGE);
    }
}
