package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.RoomDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.RoomData;
import com.epam.hotel.types.RoomClass;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the functionality when working with a "room" table of the database.
 */
public class RoomDAOImpl extends BaseDao implements RoomDAO {
    private static final String GET_FREE_ROOMS = "call get_free_rooms(?,?)";
    private static final String GET_ROOM_PRICE = "select room_cost from room where id = ?";
    private static final String GET_ROOMS_DATA = "" +
            "select distinct persons_in_room, room_class, room_cost, room_image_link from room\n" +
            "join room_class rc on room.room_class_id = rc.id\n" +
            "order by persons_in_room;";

    /**
     * Provides a getting of the free rooms based on specific date range.
     *
     * @param dateFrom a date from which rooms will be selected.
     * @param dateTo   a date till which rooms will be selected.
     * @return a list of the selected rooms.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    public ArrayList<Room> getFreeRooms(Date dateFrom, Date dateTo) throws DaoException {
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
            throw new DaoException(e);
        }
        return roomArrayList;
    }

    /**
     * Provides a getting of the room price of the specific room based on its room ID.
     *
     * @param roomID a room ID for which a room price will be provided.
     * @return a room price of the requested room.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public double getRoomPrice(int roomID) throws DaoException {
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
            throw new DaoException(e);
        }
        return roomPrice;
    }

    /**
     * Provides a getting of rooms Data.
     *
     * @return a list of rooms.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public ArrayList<RoomData> getRoomsData() throws DaoException {
        ArrayList<RoomData> roomsData = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROOMS_DATA);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                roomsData.add(getRoomData(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return roomsData;
    }

    private RoomData getRoomData(ResultSet resultSet) throws SQLException {
        return new RoomData(
                resultSet.getInt(1),
                RoomClass.valueOf(resultSet.getString(2)),
                resultSet.getDouble(3),
                resultSet.getString(4)
        );
    }
}
