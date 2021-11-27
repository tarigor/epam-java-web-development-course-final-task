package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class AdminCabinetCommand extends BaseCommand implements Command {
    private static final String ADMIN_CABINET_PAGE = "admincabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        request.setAttribute("clientRequests",adminService.getAllRequests());
        request.setAttribute("clientOrders", adminService.getAllOrders());
        doRedirect(request,response,ADMIN_CABINET_PAGE);
    }
}
