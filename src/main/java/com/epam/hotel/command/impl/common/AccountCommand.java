package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class AccountCommand extends BaseCommand implements Command {
    private static final String ACCOUNT_PAGE = "account";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        boolean orderPrepare = Boolean.parseBoolean(request.getParameter("orderPrepare"));
        if (orderPrepare) {
            request.setAttribute("roomID", Integer.parseInt(request.getParameter("roomID")));
            request.setAttribute("roomClass", request.getParameter("roomClass"));
            request.setAttribute("dateFrom", request.getParameter("dateFrom"));
            request.setAttribute("dateTo", request.getParameter("dateTo"));
            doRedirect(request, response, ACCOUNT_PAGE);
        }
        doRedirect(request, response, ACCOUNT_PAGE);
    }
}
