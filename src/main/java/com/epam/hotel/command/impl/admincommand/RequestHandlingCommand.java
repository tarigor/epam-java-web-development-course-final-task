package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.RoomData;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

/**
 * Provides the preparation to representation of the "rooms list" page while handling of the client's order by admin.
 */
public class RequestHandlingCommand extends BaseCommand implements Command {
    private static final String ROOMS_LIST_PAGE = "roomslist";

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
        try {
            long requestID = Long.parseLong(request.getParameter("requestID"));
            String email = request.getParameter("email");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            ClientRequest clientRequest = adminService.getRequest(requestID, email);
            ArrayList<Room> roomArrayList = roomService.getFreeRooms(dateFrom, dateTo);
            ArrayList<RoomData> roomsData = roomService.getRoomsData();

            request.setAttribute("clientRequest", clientRequest);
            request.setAttribute("roomArrayList", roomArrayList);
            request.setAttribute("dateFrom", dateFrom);
            request.setAttribute("dateTo", dateTo);
            request.setAttribute("roomsData", roomsData);
            doRedirect(request, response, ROOMS_LIST_PAGE);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
