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
//        Enumeration<String> e = request.getAttributeNames();
//        while (e.hasMoreElements()) {
//            String name = e.nextElement();
//            Object value = request.getAttribute(name);
//            System.out.println("name->" + name);
//            System.out.println("value->" + value);
//            attributesMap.put(name, value);
//        }
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
