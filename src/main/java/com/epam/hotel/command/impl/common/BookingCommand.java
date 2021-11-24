package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

public class BookingCommand extends BaseCommand implements Command {
    private static final String ROOM_LIST = "roomslist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        String[] dateRange = request.getParameter("datefilter").split("-");
        String dateFrom = dateRange[0].trim();
        String dateTo = dateRange[1].trim();
        System.out.println("dateFrom->" + dateFrom + " " + "dateTo->" + dateTo);
        ArrayList<Room> roomArrayList = roomService.getFreeRooms(dateFrom, dateTo);
        request.setAttribute("roomArrayList",roomArrayList);
        request.setAttribute("dateFrom", dateFrom);
        request.setAttribute("dateTo", dateTo);

        doRedirect(request, response, ROOM_LIST);
//        HashMap<RoomType, ArrayList<Integer>> mapOfAvailableRooms = roomService.getFreeRooms(dateFrom, dateTo);
//
//        System.out.println("map of rooms");
//        mapOfAvailableRooms.forEach((key, value) -> {
//            System.out.println(key.name());
//            value.forEach(System.out::println);
//        });
//
//        request.setAttribute("singleRooms",mapOfAvailableRooms.get(RoomType.SINGLE));
//        request.setAttribute("doubleRooms",mapOfAvailableRooms.get(RoomType.DOUBLE));
//        request.setAttribute("suiteRooms",mapOfAvailableRooms.get(RoomType.SUITE));
//        request.setAttribute("deluxeRooms",mapOfAvailableRooms.get(RoomType.DELUXE));
//        request.setAttribute("dateFrom",dateFrom);
//        request.setAttribute("dateTo",dateTo);
//
//        doRedirect(request,response,ROOM_LIST);
    }
}
