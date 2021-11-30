package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.Room;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

/**
 * Provides preparation for the presentation of the of the "rooms list" web page.
 */
public class CheckFreeRoomCommand extends BaseCommand implements Command {
    private static final String ROOMS_LIST = "roomslist";

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
        String[] dateRange = getDateRange(request);
        ArrayList<Room> roomArrayList = roomService.getFreeRooms(dateRange[0], dateRange[1]);

        System.out.println("list");
        roomArrayList.forEach(System.out::println);

        request.setAttribute("roomArrayList", roomArrayList);
        request.setAttribute("dateFrom", dateRange[0]);
        request.setAttribute("dateTo", dateRange[1]);
        doRedirect(request, response, ROOMS_LIST);
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
