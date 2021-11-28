package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

public class RequestHandlingCommand extends BaseCommand implements Command {
    private static final String ROOMS_LIST_PAGE = "roomslist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        long requestID = Long.parseLong(request.getParameter("requestID"));
        String email = request.getParameter("email");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo =request.getParameter("dateTo");
        ClientRequest clientRequest = adminService.getRequest(requestID,email);
        request.setAttribute("clientRequest",clientRequest);
        ArrayList<Room> roomArrayList = roomService.getFreeRooms(dateFrom, dateTo);
        request.setAttribute("roomArrayList", roomArrayList);
        request.setAttribute("dateFrom", dateFrom);
        request.setAttribute("dateTo", dateTo);
        doRedirect(request,response,ROOMS_LIST_PAGE);
    }
}
