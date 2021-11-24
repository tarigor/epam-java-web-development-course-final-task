package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.RoomDAO;
import com.epam.hotel.entity.Room;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAOImpl extends BaseDao implements RoomDAO {
    //    private static final String GET_ROOMS_WITHIN_RANGE =
//            "select distinct room_id " +
//                    "from client_order_room " +
//                    "where ? between check_in_date and check_out_date " +
//                    "or ? between check_in_date and check_out_date " +
//                    "or ? < check_in_date and ? > check_out_date " +
//                    "order by room_id";
    private static final String GET_FREE_ROOMS = "call get_free_rooms(?,?)";

//    @Override
//    public ArrayList<Integer> getRoomsWithinRange(Date dateFrom, Date dateTo) {
//        System.out.println("date From ->" + dateFrom.toString() + " " + "date To" + dateTo.toString());
//        ArrayList<Integer> roomList = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROOMS_WITHIN_RANGE);
//            preparedStatement.setDate(1, dateFrom);
//            preparedStatement.setDate(2, dateTo);
//            preparedStatement.setDate(3, dateFrom);
//            preparedStatement.setDate(4, dateTo);
//            preparedStatement.executeQuery();
//            ResultSet resultSet = preparedStatement.getResultSet();
//            while (resultSet.next()) {
//                roomList.add(resultSet.getInt(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return roomList;
//    }

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
}
