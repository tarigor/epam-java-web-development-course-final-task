package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RejectCommand extends BaseCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Integer requestID = Integer.parseInt(request.getParameter("request"));
        adminService.rejectRequest(requestID);
        response.sendRedirect(request.getContextPath() + "/command?name=admin_cabinet");
    }
}
