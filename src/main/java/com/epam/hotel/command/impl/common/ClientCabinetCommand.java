package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class ClientCabinetCommand extends BaseCommand implements Command {
    private static final String CLIENT_CABINET_PAGE = "clientcabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("clientOrders", clientService.getClientOrders(user));
        request.setAttribute("clientRequests", clientService.getClientRequests(user.getUserID()));
        doRedirect(request,response,CLIENT_CABINET_PAGE);
    }
}
