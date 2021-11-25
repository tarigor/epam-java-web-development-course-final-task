package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.AdminDAO;
import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl extends BaseDao implements AdminDAO {
    private static final String GET_ALL_ORDERS_OF_ALL_CLIENTS = "" +
            "SELECT client_order_id,\n" +
            "       first_name,\n" +
            "       last_name,\n" +
            "       email,\n" +
            "       room_id,\n" +
            "       room_class,\n" +
            "       check_in_date,\n" +
            "       check_out_date,\n" +
            "       order_status\n" +
            "FROM client_order_room\n" +
            "         join client_order c on client_order_room.client_order_id = c.client_order_room_id\n" +
            "         join user u on c.client_id = u.id\n" +
            "         join client_order co on client_order_room.client_order_id = co.client_order_room_id\n" +
            "         join room r on client_order_room.room_id = r.id\n" +
            "         join room_class rc on r.room_class_id = rc.id";
    private static final String CHANGE_ORDER_STATUS = "UPDATE hotelDB.client_order_room t SET t.order_status = ? WHERE t.client_order_id = ? AND t.room_id = ?";

    @Override
    public ArrayList<ClientOrderRoom> getAllOrdersOfAllClients() {
        ArrayList<ClientOrderRoom> clientOrderRooms = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_OF_ALL_CLIENTS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                ClientOrderRoom clientOrderRoom = new ClientOrderRoom(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        OrderStatus.valueOf(resultSet.getString(9))
                );
                clientOrderRooms.add(clientOrderRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientOrderRooms;
    }

    @Override
    public void doActionWithOrder(String orderStatus, int orderID, int roomID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ORDER_STATUS);
            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, roomID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
