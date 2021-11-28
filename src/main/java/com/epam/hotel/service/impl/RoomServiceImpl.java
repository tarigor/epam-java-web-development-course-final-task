package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.RoomDAOImpl;
import com.epam.hotel.entity.Room;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.RoomService;

import java.sql.Date;
import java.util.ArrayList;

public class RoomServiceImpl extends BaseService implements RoomService {

    private final RoomDAOImpl roomDAO = (RoomDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.ROOM_DAO);

    @Override
    public ArrayList<Room> getFreeRooms(String dateFrom, String dateTo) {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        ArrayList<Room> roomsList =
                transaction.createConnection().performTransaction(() -> roomDAO.getFreeRooms(dateFromSQL, dateToSQL));
        roomsList.forEach(System.out::println);
        return roomsList;
    }

    @Override
    public double getRoomPrice(int roomID) {
        return transaction.createConnection().performTransaction(() -> roomDAO.getRoomPrice(roomID));
    }
}
