package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

public class CheckFreeRoomCommand extends BaseCommand implements Command {
    private static final String ROOM_LIST = "roomslist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        String[] dateRange = getDateRange(request);
        ArrayList<Room> roomArrayList = roomService.getFreeRooms(dateRange[0], dateRange[1]);
        request.setAttribute("roomArrayList", roomArrayList);
        request.setAttribute("dateFrom", dateRange[0]);
        request.setAttribute("dateTo", dateRange[1]);
        doRedirect(request, response, ROOM_LIST);
    }

    private String[] getDateRange(HttpServletRequest request) {
        String dateFrom;
        String dateTo;
        if (request.getParameter("dateFrom") != null && request.getParameter("dateTo") != null) {
            dateFrom = request.getParameter("dateFrom");
            dateTo = request.getParameter("dateTo");
        } else {
            String[] dateRange = request.getParameter("datefilter").split("-");
            dateFrom = dateRange[0].trim();
            dateTo = dateRange[1].trim();
        }
        return new String[]{dateFrom, dateTo};
    }
}
