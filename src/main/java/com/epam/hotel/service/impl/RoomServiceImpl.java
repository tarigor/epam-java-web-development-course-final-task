package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.RoomDAOImpl;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.RoomType;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.RoomService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomServiceImpl extends BaseService implements RoomService {

    private final RoomDAOImpl roomDAO = (RoomDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.ROOM_DAO);


//    public HashMap<RoomType, ArrayList<Integer>> getFreeRooms2(String dateFrom, String dateTo) {
//        Date dateFromSQL = convertStringSqlDate(dateFrom);
//        Date dateToSQL = convertStringSqlDate(dateTo);
//        ArrayList<Integer> roomsList =
//                transaction.createConnection().performTransaction(() -> roomDAO.getRoomsWithinRange(dateFromSQL, dateToSQL));
//        System.out.println("busy rooms:");
//        roomsList.forEach(System.out::println);
//
//        ArrayList<Integer> listOfAvailableRooms = getFreeRoomList(roomsList);
//        System.out.println("available rooms:");
//        listOfAvailableRooms.forEach(System.out::println);
//
//        HashMap<RoomType, ArrayList<Integer>> mapOfAvailableRooms = getMapOfAvailableRooms(listOfAvailableRooms);
//
//        return mapOfAvailableRooms;
//    }

    @Override
    public ArrayList<Room> getFreeRooms(String dateFrom, String dateTo){
        Date dateFromSQL = convertStringSqlDate(dateFrom);
        Date dateToSQL = convertStringSqlDate(dateTo);
        ArrayList<Room> roomsList =
                transaction.createConnection().performTransaction(() -> roomDAO.getFreeRooms(dateFromSQL, dateToSQL));
        roomsList.forEach(System.out::println);
        return roomsList;
    }

//    private HashMap<RoomType, ArrayList<Integer>> getMapOfAvailableRooms(ArrayList<Integer> listOfAvailableRooms) {
//        HashMap<RoomType, ArrayList<Integer>> mapOfAvailableRooms = new HashMap<>();
//        ArrayList<Integer> singleRoomList = new ArrayList<>();
//        ArrayList<Integer> doubleRoomList = new ArrayList<>();
//        ArrayList<Integer> suiteRoomList = new ArrayList<>();
//        ArrayList<Integer> deluxeRoomList = new ArrayList<>();
//
//        for (int i = 0; i < listOfAvailableRooms.size(); i++) {
//            if (listOfAvailableRooms.get(i) < 6) {
//                singleRoomList.add(listOfAvailableRooms.get(i));
//            } else if (listOfAvailableRooms.get(i) > 5 & listOfAvailableRooms.get(i) < 10) {
//                doubleRoomList.add(listOfAvailableRooms.get(i));
//            } else if (listOfAvailableRooms.get(i) > 9 & listOfAvailableRooms.get(i) < 13) {
//                suiteRoomList.add(listOfAvailableRooms.get(i));
//            } else if (listOfAvailableRooms.get(i) > 12 & listOfAvailableRooms.get(i) < 15) {
//                deluxeRoomList.add(listOfAvailableRooms.get(i));
//            }
//        }
//        mapOfAvailableRooms.put(RoomType.SINGLE, singleRoomList);
//        mapOfAvailableRooms.put(RoomType.DOUBLE, doubleRoomList);
//        mapOfAvailableRooms.put(RoomType.SUITE, suiteRoomList);
//        mapOfAvailableRooms.put(RoomType.DELUXE, deluxeRoomList);
//
//        return mapOfAvailableRooms;
//    }
//
//    private ArrayList<Integer> getFreeRoomList(ArrayList<Integer> roomsList) {
//        ArrayList<Integer> listOfAvailableRooms = new ArrayList<>();
//        for (int i = 1; i < 15; i++) {
//            boolean isExist = false;
//            for (Integer item : roomsList) {
//                if (item.equals(i)) {
//                    isExist = true;
//                }
//            }
//            if(!isExist){
//                listOfAvailableRooms.add(i);
//            }
//        }
//        return listOfAvailableRooms;
//    }

    private Date convertStringSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSQL;
    }
}
