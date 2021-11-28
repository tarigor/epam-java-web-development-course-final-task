package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.RoomDAO;
import com.epam.hotel.entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomDAOImpl extends BaseDao implements RoomDAO {
    private static final String GET_FREE_ROOMS = "call get_free_rooms(?,?)";
    private static final String GET_ROOM_PRICE = "select room_cost from room where id = ?";

    public ArrayList<Room> getFreeRooms(Date dateFrom, Date dateTo) {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall(GET_FREE_ROOMS);
            callableStatement.setDate(1, dateFrom);
            callableStatement.setDate(2, dateTo);
            callableStatement.executeQuery();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomID(resultSet.getString(1));
                room.setRoomType(resultSet.getString(2));
                roomArrayList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomArrayList;
    }

    @Override
    public double getRoomPrice(int roomID) {
        double roomPrice = 0.0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROOM_PRICE);
            preparedStatement.setInt(1, roomID);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                roomPrice = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomPrice;
    }
}
